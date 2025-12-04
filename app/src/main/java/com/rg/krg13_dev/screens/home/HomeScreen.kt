package com.rg.krg13_dev.screens.home

import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
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
import com.pinappall.integration.*
import com.rg.krg13_dev.R
import com.rg.krg13_dev.autocomputer.AutoComputerViewModel
import com.rg.krg13_dev.navigation.Screen
import com.rg.krg13_dev.pinappall.PaymentViewModel
import com.rg.krg13_dev.ui.components.*
import com.rg.krg13_dev.utils.SoundManager
import kotlinx.coroutines.flow.collectLatest

@Composable
fun HomeScreen(
    navController: NavHostController
) {
    // ======================= AUTOCOMPUTER =======================
    val context = LocalContext.current
    val autoComputerViewModel: AutoComputerViewModel =
        viewModel(context as ComponentActivity)

    val isConnected by autoComputerViewModel.isConnected.collectAsState()
    val isLocked by autoComputerViewModel.isLocked.collectAsState()
    val course by autoComputerViewModel.courseParams.collectAsState()

    val boardingStop = course?.T_boardingStopName?.ifBlank { "-" } ?: "-"
    val alightingStop = course?.t_alightingStopName?.ifBlank { "-" } ?: "-"

    // ======================= PAYMENT VM (SZYBKI ZAKUP) =======================
    val paymentVM: PaymentViewModel = viewModel()

    val launcher = rememberLauncherForActivityResult(
        contract = ImplementationApi.createTransactionResultContract()
    ) { result ->
        if (result.success) {
            paymentVM.onResult(result.data)
        } else {
            paymentVM.onResult(null)
        }
    }

    // Obsługa płatności tak jak w MultiTicketScreen
    LaunchedEffect(Unit) {
        paymentVM.lastRecord.collectLatest { record ->
            if (record == null) return@collectLatest

            when (record.status) {

                TransactionStatus.APPROVED ->
                    navController.navigate(
                        Screen.TicketSuccess.pass(record.amount / 100.0)
                    )

                TransactionStatus.DENIED ->
                    navController.navigate(Screen.TicketFail.route)

                TransactionStatus.UNKNOWN ->
                    navController.navigate(Screen.TicketCancelled.route)
            }

            paymentVM.clear()
        }
    }

    // ======================= UI =======================
    Box(modifier = Modifier.fillMaxSize()) {

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color(0xFF0A1A24)
        ) {

            Column(
                modifier = Modifier.fillMaxSize()
            ) {

                // ---------------- GÓRNA BELKA ----------------
                TopPaymentHeader(
                    modifier = Modifier.background(Color(0xFF0A1A24))
                )

                // ---------------- PRZYSTANKI ----------------
                StopsSection(
                    boardingStop = boardingStop,
                    alightingStop = alightingStop
                )

                Spacer(Modifier.height(36.dp))

                // ---------------- KAFELKI ----------------
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {

                    // 🔵 BILET NORMALNY — szybki zakup
                    TicketButton(
                        title = stringResource(R.string.ticket_normal),
                        price = "3.20 zł"
                    ) {
                        SoundManager.playClick()
                        paymentVM.startPayment(launcher, (3.20 * 100).toInt())
                    }

                    Spacer(Modifier.height(12.dp))

                    // 🟢 BILET ULGOWY — szybki zakup
                    TicketButton(
                        title = stringResource(R.string.ticket_reduced),
                        price = "1.60 zł"
                    ) {
                        SoundManager.playClick()
                        paymentVM.startPayment(launcher, (1.60 * 100).toInt())
                    }

                    Spacer(Modifier.height(12.dp))

                    // ↗️ BILETY WIELOKROTNE — przejście do ekranu
                    TicketButtonArrow(
                        title = stringResource(R.string.ticket_multi)
                    ) {
                        SoundManager.playClick()
                        navController.navigate(Screen.MultiTicket.route)
                    }
                }

                Spacer(modifier = Modifier.weight(1f))
            }
        }

        // ---------------- DÓŁ EKRANU ----------------
        Column(
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            LanguageBar(Modifier.fillMaxWidth())
            BottomLogosBar(Modifier.fillMaxWidth())
        }

        // ---------------- NAKŁADKI ----------------
        if (!isConnected) NoCommunicationOverlay()
        if (isLocked) TicketControlLockOverlay()
    }
}
