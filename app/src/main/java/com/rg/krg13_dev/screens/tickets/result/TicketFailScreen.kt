package com.rg.krg13_dev.screens.tickets.result

import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.rg.krg13_dev.R
import com.rg.krg13_dev.autocomputer.AutoComputerViewModel
import com.rg.krg13_dev.navigation.Screen
import com.rg.krg13_dev.ui.components.BottomLogosBar
import com.rg.krg13_dev.ui.components.TopPaymentHeader
import com.rg.krg13_dev.ui.components.StopsSection
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun TicketFailScreen(
    navController: NavHostController
) {
    // AUTO POWRÓT PO 5 SEKUNDACH
    LaunchedEffect(Unit) {
        delay(5000)
        navController.navigate(Screen.Home.route) {
            popUpTo(Screen.Home.route) { inclusive = true }
        }
    }

    // AUTOCOMPUTER VM
    val context = LocalContext.current
    val autoComputerViewModel: AutoComputerViewModel =
        viewModel(context as ComponentActivity)

    val course by autoComputerViewModel.courseParams.collectAsState()
    val boardingStop = course?.T_boardingStopName ?: "-"
    val alightingStop = course?.t_alightingStopName ?: "-"

    // CLOCK
    var time by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        while (true) {
            val now = Date()
            time = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(now)
            date = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(now)
            delay(1000)
        }
    }

    // ================= UI =================
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0A1A24))
    ) {

        Column(modifier = Modifier.fillMaxSize()) {

            // TOP HEADER
            TopPaymentHeader()

            // STOPS
            StopsSection(
                boardingStop = boardingStop,
                alightingStop = alightingStop
            )

            Spacer(Modifier.height(40.dp))

            // CENTER CONTENT (FAIL)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = stringResource(R.string.icon_fail),
                    fontSize = 100.sp,
                    color = Color.White
                )

                Spacer(Modifier.height(24.dp))

                Text(
                    text = stringResource(R.string.ticket_fail_title),
                    color = Color.White,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(Modifier.height(16.dp))

                Text(
                    text = stringResource(R.string.ticket_fail_reason),
                    color = Color(0xFFAFE1FF),
                    fontSize = 20.sp
                )
            }

            Spacer(modifier = Modifier.weight(1f))
        }

        BottomLogosBar(
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}
