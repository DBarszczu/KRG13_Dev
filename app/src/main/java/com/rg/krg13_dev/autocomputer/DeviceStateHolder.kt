package com.rg.krg13_dev.autocomputer

import kotlinx.coroutines.flow.MutableStateFlow

object DeviceStateHolder {
    val isDeviceLocked_NoCommunication = MutableStateFlow(true)
}
