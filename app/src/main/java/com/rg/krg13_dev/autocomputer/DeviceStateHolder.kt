package com.rg.krg13_dev.autocomputer

import kotlinx.coroutines.flow.MutableStateFlow

object DeviceStateHolder {
    val isDeviceLocked_NoCommunication = MutableStateFlow(true)
    val isDeviceLocked_TicketControl = MutableStateFlow(false)
    val isBroadcastActive = MutableStateFlow(true)

    // na przyszłość (np. SETJ)
    var lastParsedCourseParams: Any? = null
}
