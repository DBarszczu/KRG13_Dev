package com.rg.krg13_dev.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.rg.krg13_dev.screens.debug.NetDebugScreen
import com.rg.krg13_dev.screens.deviceInfo.DeviceInfoScreen
import com.rg.krg13_dev.screens.home.HomeScreen

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
            HomeScreen()
        }
        composable(Screen.NetDebug.route) {
            NetDebugScreen()
        }
    }
}
