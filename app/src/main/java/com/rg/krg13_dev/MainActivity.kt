package com.rg.krg13_dev

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.MotionEvent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.rg.krg13_dev.navigation.AppNavHost
import com.rg.krg13_dev.navigation.Screen
import com.rg.krg13_dev.ui.theme.KRG13_DevTheme
import com.rg.krg13_dev.utils.LocaleManager
import com.rg.krg13_dev.utils.SoundManager

class MainActivity : ComponentActivity() {

    companion object {
        private const val IDLE_TIMEOUT = 60_000L // 60 sekund
    }

    private val idleHandler = Handler(Looper.getMainLooper())
    private lateinit var navController: NavHostController

    private val idleRunnable = Runnable {
        navigateToHome()
    }

    override fun attachBaseContext(newBase: Context?) {
        val wrapped = LocaleManager.wrapContext(newBase!!)
        super.attachBaseContext(wrapped)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        hideSystemBars()
        enableEdgeToEdge()
        SoundManager.init(this)

        setContent {
            KRG13_DevTheme {
                navController = rememberNavController()
                AppNavHost(navController = navController)
            }
        }
    }

    // 🔥 GLOBALNE łapanie każdego dotknięcia
    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        resetIdleTimer()
        return super.dispatchTouchEvent(ev)
    }

    override fun onResume() {
        super.onResume()
        resetIdleTimer()
    }

    override fun onPause() {
        super.onPause()
        idleHandler.removeCallbacks(idleRunnable)
    }

    private fun resetIdleTimer() {
        idleHandler.removeCallbacks(idleRunnable)
        idleHandler.postDelayed(idleRunnable, IDLE_TIMEOUT)
    }

    private fun navigateToHome() {
        if (!::navController.isInitialized) return

        navController.navigate(Screen.Home.route) {
            popUpTo(navController.graph.startDestinationId) {
                inclusive = false
            }
            launchSingleTop = true
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
