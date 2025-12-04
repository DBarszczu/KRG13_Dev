package com.rg.krg13_dev.pinappall

import com.pinappall.integration.Result
import com.pinappall.integration.InstallationStatus
import com.pinappall.integration.ConfigurationStatus
import com.pinappall.integration.ConfigurationDeviceStatus

data class PinAppAllState(
    val connected: Boolean,
    val installationStatus: Result<InstallationStatus>?,
    val configurationStatus: Result<ConfigurationStatus>?,
    val configurationResponse: Result<ConfigurationDeviceStatus>?
)