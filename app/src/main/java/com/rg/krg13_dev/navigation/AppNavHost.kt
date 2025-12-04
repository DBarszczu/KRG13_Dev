package com.rg.krg13_dev.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.rg.krg13_dev.screens.debug.NetDebug2Screen
import com.rg.krg13_dev.screens.debug.NetDebugScreen
import com.rg.krg13_dev.screens.deviceInfo.DeviceInfoScreen
import com.rg.krg13_dev.screens.home.HomeScreen
import com.rg.krg13_dev.screens.tickets.MultiTicketScreen
import com.rg.krg13_dev.screens.tickets.result.TicketCancelledScreen
import com.rg.krg13_dev.screens.tickets.result.TicketFailScreen
import com.rg.krg13_dev.screens.tickets.result.TicketSuccessScreen
import com.rg.krg13_dev.screens.tickets.result.TicketTokenScreen

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.DeviceInfo.route
    ) {
        composable(Screen.DeviceInfo.route) {
            DeviceInfoScreen(onContinue = {
                navController.navigate(Screen.Home.route) {
                    popUpTo(Screen.DeviceInfo.route) { inclusive = true }
                }
            })
        }

        composable(Screen.Home.route) {
            HomeScreen(navController)
        }

        composable(Screen.MultiTicket.route) {
            MultiTicketScreen(navController)
        }

        composable(Screen.TicketFail.route) {
            TicketFailScreen(navController)
        }

        composable(Screen.TicketToken.route) {
            TicketTokenScreen(navController)
        }

        composable(Screen.TicketCancelled.route) {
            TicketCancelledScreen(navController)
        }

        composable(Screen.TicketSuccess.route) { backStackEntry ->
            val amount = backStackEntry.arguments
                ?.getString("amount")
                ?.toDoubleOrNull() ?: 0.0

            TicketSuccessScreen(navController, amount)
        }

        composable(Screen.NetDebug.route) { NetDebugScreen() }
        composable(Screen.NetDebug2.route) { NetDebug2Screen() }
    }
}
