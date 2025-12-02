package com.rg.krg13_dev.navigation

sealed class Screen(val route: String) {
    object DeviceInfo : Screen("device_info")
    object Home : Screen("home")
    object MultiTicket : Screen("multi_ticket")
    object NetDebug : Screen("debug_network")
    object NetDebug2 : Screen("debug2")


}
