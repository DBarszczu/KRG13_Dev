package com.rg.krg13_dev.screens.tickets

import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.rg.krg13_dev.ui.components.BottomLogosBar
import com.rg.krg13_dev.ui.components.NoCommunicationOverlay
import com.rg.krg13_dev.ui.components.TicketCounter
import com.rg.krg13_dev.ui.components.TopPaymentHeader
import com.rg.krg13_dev.ui.components.StopsSection
import com.rg.krg13_dev.ui.components.TicketControlLockOverlay
import com.rg.krg13_dev.utils.SoundManager
import com.rg.krg13_dev.utils.formatPrice
import kotlinx.coroutines.flow.collectLatest

@Composable
fun MultiTicketScreen(navController: NavHostController) {

    val context = LocalContext.current

    val autoComputerViewModel: AutoComputerViewModel =
        viewModel(context as ComponentActivity)
    val isUiBlocked by autoComputerViewModel
        .isUiBlocked
        .collectAsState()

    val isLocked by autoComputerViewModel.isLocked.collectAsState()

    val bankPrices by autoComputerViewModel
        .bankTicketPrices
        .collectAsState()

    val hasTariff = bankPrices != null


    val course by autoComputerViewModel.courseParams.collectAsState()
    val boardingStop = course?.T_boardingStopName ?: "-"
    val alightingStop = course?.t_alightingStopName ?: "-"

    // ======================= PAYMENT VM =======================
    val paymentVM: PaymentViewModel = viewModel()

    val launcher = rememberLauncherForActivityResult(
        contract = ImplementationApi.createTransactionResultContract()
    ) { result: Result<TransactionRecord> ->

        if (result.success) {
            paymentVM.onResult(result.data)
        } else {
            Log.e("PAYMENT", "Transaction failed: ${result.problems}")
            paymentVM.onResult(null)
        }
    }



    // ======================= OBSŁUGA PŁATNOŚCI =======================
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

    // ======================= LICZNIKI =======================
    var normalCount by remember { mutableStateOf(0) }
    var reducedCount by remember { mutableStateOf(0) }

    // ceny z taryfy (grosze)
    val priceNormalGrosz = bankPrices?.first ?: 0
    val priceReducedGrosz = bankPrices?.second ?: 0

    // suma w groszach (BEZ Double!)
    val totalPriceGrosz =
        normalCount * priceNormalGrosz +
                reducedCount * priceReducedGrosz


    // ======================= UI =======================
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0A1A24))
    ) {

        Column(modifier = Modifier.fillMaxSize()) {

            TopPaymentHeader()

            StopsSection(
                boardingStop = boardingStop,
                alightingStop = alightingStop
            )

            Spacer(Modifier.height(24.dp))

            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {

                TicketCounter(
                    label = stringResource(R.string.ticket_normal),
                    price = formatPrice(priceNormalGrosz),
                    count = normalCount,
                    onMinus = {
                        if (!hasTariff) return@TicketCounter
                        SoundManager.playClick()
                        if (normalCount > 0) normalCount--
                    },
                    onPlus = {
                        if (!hasTariff) return@TicketCounter
                        SoundManager.playClick()
                        normalCount++
                    }
                )


                Spacer(Modifier.height(16.dp))

                TicketCounter(
                    label = stringResource(R.string.ticket_reduced),
                    price = formatPrice(priceReducedGrosz),
                    count = reducedCount,
                    onMinus = {
                        if (!hasTariff) return@TicketCounter
                        SoundManager.playClick()
                        if (reducedCount > 0) reducedCount--
                    },
                    onPlus = {
                        if (!hasTariff) return@TicketCounter
                        SoundManager.playClick()
                        reducedCount++
                    }
                )


                Spacer(Modifier.height(24.dp))

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        stringResource(R.string.total_to_pay),
                        color = Color.White,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        formatPrice(totalPriceGrosz),
                        color = Color(0xFFAFE1FF),
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Bold
                    )
                }



                Spacer(Modifier.height(24.dp))

                Row(Modifier.fillMaxWidth()) {

                    Button(
                        onClick = {
                            SoundManager.playClick()
                            navController.popBackStack()
                                  },
                        colors = ButtonDefaults.buttonColors(Color(0xFF3B4F63)),
                        modifier = Modifier.weight(1f).height(70.dp)
                    ) {
                        Text("‹ " + stringResource(R.string.back_button),
                            color = Color.White, fontSize = 24.sp)
                    }

                    Spacer(Modifier.width(12.dp))

                    Button(
                        onClick = {
                            if (!hasTariff) return@Button
                            SoundManager.playClick()
                            if (totalPriceGrosz > 0) {
                                paymentVM.startPayment(
                                    launcher,
                                    totalPriceGrosz
                                )
                            }
                        },
                        enabled = hasTariff && totalPriceGrosz > 0,
                        colors = ButtonDefaults.buttonColors(
                            if (hasTariff) Color(0xFF577CA0) else Color(0xFF3B4F63)
                        ),
                                modifier = Modifier.weight(1f).height(70.dp)
                    ) {
                        Text(stringResource(R.string.purchase_button),
                            color = Color.White, fontSize = 24.sp)
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))
            BottomLogosBar(Modifier.fillMaxWidth())
        }
        if (isUiBlocked) {
            NoCommunicationOverlay()
        }

        if (isLocked) {
            TicketControlLockOverlay()
        }

    }
}
