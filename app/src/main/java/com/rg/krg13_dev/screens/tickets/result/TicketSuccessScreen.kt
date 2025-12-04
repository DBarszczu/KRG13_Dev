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
fun TicketSuccessScreen(
    navController: NavHostController,
    amount: Double
) {
    // ====== AUTO POWRÓT PO 5 SEKUNDACH ======
    LaunchedEffect(Unit) {
        delay(5000)
        navController.navigate(Screen.Home.route) {
            popUpTo(Screen.Home.route) { inclusive = true }
            launchSingleTop = true
        }
    }

    // ====== AUTOCOMPUTER VIEWMODEL ======
    val context = LocalContext.current
    val autoComputerViewModel: AutoComputerViewModel =
        viewModel(context as ComponentActivity)

    val course by autoComputerViewModel.courseParams.collectAsState()
    val boardingStop = course?.T_boardingStopName ?: "-"
    val alightingStop = course?.t_alightingStopName ?: "-"

    // ====== ZEGAR ======
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

            // ---------------- GÓRNA BELKA (KOMPONENT) ----------------
            TopPaymentHeader(
                modifier = Modifier.background(Color(0xFF0A1A24))
            )

            // ---------------- PRZYSTANKI (KOMPONENT) ----------------
            StopsSection(
                boardingStop = boardingStop,
                alightingStop = alightingStop
            )

            Spacer(Modifier.height(40.dp))

            // ======= ŚRODEK EKRANU =======
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // Ikona ✔️ z tłumaczeń
                Text(
                    text = stringResource(id = R.string.icon_success),
                    fontSize = 100.sp,
                    color = Color.White
                )

                Spacer(Modifier.height(24.dp))

                // Nagłówek "ZAKUPIONO BILET ELEKTRONICZNY"
                Text(
                    text = stringResource(R.string.ticket_success_title),
                    color = Color.White,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(Modifier.height(16.dp))

                // Kwota "Pobrano X zł"
                Text(
                    text = stringResource(
                        R.string.ticket_success_amount,
                        String.format("%.2f", amount)
                    ),
                    color = Color(0xFFAFE1FF),
                    fontSize = 22.sp
                )
            }

            Spacer(modifier = Modifier.weight(1f))
        }

        // ======= LOGO NA DOLE =======
        BottomLogosBar(
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}
