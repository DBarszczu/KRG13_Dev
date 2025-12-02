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
    // UWAGA: na starcie ma być "brak komunikacji"
    private val _isConnected = MutableStateFlow(false)
    val isConnected: StateFlow<Boolean> get() = _isConnected

    // 🔒 blokada "Kontrola Biletów"
    private val _isLocked = MutableStateFlow(false)
    val isLocked: StateFlow<Boolean> get() = _isLocked

    // logika opóźnienia wykrycia braku komunikacji
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
    // CALLBACKI Z MANAGERA – WOŁANE PRZEZ AutoComputerManager
    // ----------------------------------------------------

    /**
     * Odebrano ramkę UDP → komunikacja OK.
     *
     * WYMAGANIE:
     * - po powrocie komunikacji blokada ma być zdjęta
     *   (DEVICE_LOCKED_FLAG = false, ekran blokady znika)
     */
    fun onCommunicationRestored() {
        disconnectTimestamp = 0L

        // jeżeli wracamy z "braku komunikacji", to upewniamy się,
        // że blokada jest zdjęta logicznie i w statusie
        if (!_isConnected.value) {
            // UI – zdejmujemy ewentualną blokadę
            if (_isLocked.value) {
                _isLocked.value = false
            }

            // STATUS – zdejmujemy flagę DEVICE_LOCKED_FLAG
            statusManager.setFlag("DEVICE_LOCKED_FLAG", false)
            statusManager.updateStatusFlags(status)
        }

        _isConnected.value = true
    }

    /**
     * Wywoływane przez watchdog w AutoComputerManager, gdy długo nie ma ramek.
     *
     * WYMAGANIE:
     * - jeśli kasownik był zablokowany, a potem nie ma komunikacji:
     *   → blokada ma zniknąć
     *   → ma pojawić się "brak komunikacji"
     */
    fun onNoCommunication() {
        if (disconnectTimestamp == 0L) {
            // pierwsze wykrycie – start odliczania
            disconnectTimestamp = System.currentTimeMillis()
            return
        }

        val elapsed = System.currentTimeMillis() - disconnectTimestamp

        if (elapsed >= disconnectDelay) {
            // już jesteśmy w stanie "brak komunikacji" – nic więcej nie rób
            if (!_isConnected.value) return

            // przechodzimy w stan braku komunikacji:
            // 1) zdejmujemy blokadę w UI
            if (_isLocked.value) {
                _isLocked.value = false
            }

            // 2) zdejmujemy flagę DEVICE_LOCKED_FLAG w statusie
            statusManager.setFlag("DEVICE_LOCKED_FLAG", false)
            statusManager.updateStatusFlags(status)

            // 3) ustawiamy brak komunikacji
            _isConnected.value = false
        }
    }

    /** Komenda LOCK (0x05) – z AutoComputerManager */
    fun onDeviceLocked() {
        _isLocked.value = true
    }

    /** Komenda UNLOCK (0x06) – z AutoComputerManager */
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
    // RĘCZNE BLOKOWANIE (opcjonalne z UI)
    // ----------------------------------------------------
    fun onLock() { _isLocked.value = true }
    fun onUnlock() { _isLocked.value = false }
}
