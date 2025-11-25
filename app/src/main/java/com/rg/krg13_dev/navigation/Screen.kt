package com.rg.krg13_dev.navigation

sealed class Screen(val route: String) {
    object DeviceInfo : Screen("device_info")
    object Home : Screen("home")
    object NetDebug : Screen("debug_network")

}
