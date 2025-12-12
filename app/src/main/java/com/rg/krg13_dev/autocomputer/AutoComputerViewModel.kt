package com.rg.krg13_dev.autocomputer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rg.krg13_dev.autocomputer.parser.CourseParameter
import com.rg.krg13_dev.autocomputer.parser.SetJPars
import com.rg.krg13_dev.autocomputer.parser.Stop
import com.rg.krg13_dev.autocomputer.parser.StopsParser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.net.DatagramPacket

class AutoComputerViewModel : ViewModel() {

    // ----------------------------------------------------
    // PODSTAWOWE OBIEKTY
    // ----------------------------------------------------

    val status = AutoComputerStatus()
    val statusManager = AutoComputerStatusManager()
    val data = Data()


    // ----------------------------------------------------
    // STANY DLA UI
    // ----------------------------------------------------

    private val _courseParams = MutableStateFlow<CourseParameter?>(null)
    val courseParams: StateFlow<CourseParameter?> get() = _courseParams

    private val _stops = MutableStateFlow<List<Stop>>(emptyList())
    val stops: StateFlow<List<Stop>> get() = _stops


    // 🔌 komunikacja
    private val _isConnected = MutableStateFlow(false)
    val isConnected: StateFlow<Boolean> get() = _isConnected

    // 🔒 blokada
    private val _isLocked = MutableStateFlow(false)
    val isLocked: StateFlow<Boolean> get() = _isLocked

    private var disconnectTimestamp = 0L
    private val disconnectDelay = 5000L


    // ----------------------------------------------------
    // MANAGER UDP
    // ----------------------------------------------------

    private val manager = AutoComputerManager(
        statusManager = statusManager,
        status = status,
        data = data,
        viewModel = this
    )

    init {
        manager.start(viewModelScope)
    }

    override fun onCleared() {
        super.onCleared()
        manager.stop()
    }


    // ----------------------------------------------------
    // CALLBACKI – z AutoComputerManager
    // ----------------------------------------------------

    fun onCommunicationRestored() {
        disconnectTimestamp = 0L

        if (!_isConnected.value) {
            if (_isLocked.value) _isLocked.value = false
            statusManager.setFlag("DEVICE_LOCKED_FLAG", false)
            statusManager.updateStatusFlags(status)
        }

        _isConnected.value = true
    }

    fun onNoCommunication() {
        if (disconnectTimestamp == 0L) {
            disconnectTimestamp = System.currentTimeMillis()
            return
        }

        if (System.currentTimeMillis() - disconnectTimestamp >= disconnectDelay) {
            if (!_isConnected.value) return

            if (_isLocked.value) _isLocked.value = false
            statusManager.setFlag("DEVICE_LOCKED_FLAG", false)
            statusManager.updateStatusFlags(status)

            _isConnected.value = false
        }
    }

    fun onDeviceLocked() {
        _isLocked.value = true
    }

    fun onDeviceUnlocked() {
        _isLocked.value = false
    }


    // ----------------------------------------------------
    // DANE KURSU - SETJ
    // ----------------------------------------------------

    fun updateCourse(raw: String) {
        val parsed = SetJPars().parse(raw)
        _courseParams.value = parsed
    }

    fun onNewCourse(parameters: CourseParameter) {
        _courseParams.value = parameters
    }


    // ----------------------------------------------------
    // LISTA PRZYSTANKÓW
    // ----------------------------------------------------

    fun updateStops(packet: DatagramPacket) {
        val parsed = StopsParser.parse(packet)
        _stops.value = parsed
    }

    fun onNewStops(list: List<Stop>) {
        _stops.value = list
    }


    // ----------------------------------------------------
    // RĘCZNE BLOKOWANIE
    // ----------------------------------------------------

    fun onLock() { _isLocked.value = true }
    fun onUnlock() { _isLocked.value = false }
}
