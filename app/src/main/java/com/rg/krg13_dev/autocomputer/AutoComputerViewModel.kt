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


    // 🔌 komunikacja OK / brak komunikacji
    private val _isConnected = MutableStateFlow(true)
    val isConnected: StateFlow<Boolean> get() = _isConnected

    // 🔒 blokada "Kontrola Biletów"
    private val _isLocked = MutableStateFlow(false)
    val isLocked: StateFlow<Boolean> get() = _isLocked

    private var disconnectTimestamp = 0L
    private val disconnectDelay = 5000L // 5 sekund


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
    // CALLBACKI Z MANAGERA – *WOŁANE PRZEZ AutoComputerManager*
    // ----------------------------------------------------

    /** Odebrano ramkę UDP → komunikacja OK */
    fun onCommunicationRestored() {
        disconnectTimestamp = 0L
        _isConnected.value = true
    }

    /** Brak ramek → START odliczania */
    fun onNoCommunication() {
        if (disconnectTimestamp == 0L) {
            disconnectTimestamp = System.currentTimeMillis()
            return
        }

        val elapsed = System.currentTimeMillis() - disconnectTimestamp

        if (elapsed >= disconnectDelay) {
            _isConnected.value = false
            _isLocked.value = false
        }
    }

    /** Komenda LOCK (0x05) */
    fun onDeviceLocked() {
        _isLocked.value = true
    }

    /** Komenda UNLOCK (0x06) */
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

    /** Manager używa tego */
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
    // RĘCZNE BLOKOWANIE (opcjonalne)
    // ----------------------------------------------------
    fun onLock() { _isLocked.value = true }
    fun onUnlock() { _isLocked.value = false }
}
