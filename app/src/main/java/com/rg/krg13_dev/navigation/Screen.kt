package com.rg.krg13_dev.navigation

sealed class Screen(val route: String) {

    object DeviceInfo : Screen("device_info")
    object Home : Screen("home")
    object MultiTicket : Screen("multi_ticket")

    object TicketCancelled : Screen("ticket_cancelled")
    object TicketFail : Screen("ticket_fail")
    object TicketToken : Screen("ticket_token")

    object TicketSuccess : Screen("ticket_success/{amount}") {
        fun pass(amount: Double) = "ticket_success/$amount"
    }

    object NetDebug : Screen("debug_network")
    object NetDebug2 : Screen("debug2")
}
