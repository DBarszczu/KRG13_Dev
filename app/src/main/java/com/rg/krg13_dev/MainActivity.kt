package com.rg.krg13_dev

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.rg.krg13_dev.navigation.AppNavHost
import com.rg.krg13_dev.ui.theme.KRG13_DevTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            KRG13_DevTheme {
                val navController = rememberNavController()

                AppNavHost(navController = navController)
            }
        }
    }
}
