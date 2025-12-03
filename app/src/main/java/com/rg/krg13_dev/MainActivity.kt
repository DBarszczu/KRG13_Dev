package com.rg.krg13_dev

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.compose.rememberNavController
import com.rg.krg13_dev.autocomputer.AutoComputerViewModel
import com.rg.krg13_dev.navigation.AppNavHost
import com.rg.krg13_dev.ui.theme.KRG13_DevTheme
import com.rg.krg13_dev.utils.LocaleManager
import com.rg.krg13_dev.utils.SoundManager

class MainActivity : ComponentActivity() {

    private val autoComputerViewModel: AutoComputerViewModel by viewModels()

    // 🔥 KLUCZOWE — PRZEŁADOWANIE KONTEKSTU NA BAZIE ZAPISANEGO JĘZYKA
    override fun attachBaseContext(newBase: Context?) {
        val wrapped = LocaleManager.wrapContext(newBase!!)
        super.attachBaseContext(wrapped)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        hideSystemBars()  // Twoje — NIE USUWAMY

        enableEdgeToEdge()
        SoundManager.init(this)

        setContent {
            KRG13_DevTheme {
                val navController = rememberNavController()
                AppNavHost(navController = navController)
            }
        }
    }

    private fun hideSystemBars() {
        WindowCompat.setDecorFitsSystemWindows(window, false)

        val controller = WindowInsetsControllerCompat(window, window.decorView)
        controller.hide(WindowInsetsCompat.Type.systemBars())

        controller.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
    }
}
