package com.rg.krg13_dev.screens.tickets.result

import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.rg.krg13_dev.autocomputer.AutoComputerViewModel
import com.rg.krg13_dev.ui.components.BottomLogosBar
import com.rg.krg13_dev.ui.components.TopPaymentHeader
import com.rg.krg13_dev.ui.components.StopsSection

@Composable
fun TicketTokenScreen(
    navController: NavHostController
) {
    // ==== AUTOCOMPUTER VIEWMODEL ====
    val context = LocalContext.current
    val autoComputerViewModel: AutoComputerViewModel =
        viewModel(context as ComponentActivity)

    val course by autoComputerViewModel.courseParams.collectAsState()
    val boardingStop = course?.T_boardingStopName ?: "-"
    val alightingStop = course?.t_alightingStopName ?: "-"

    // ==== UI ====
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0A1A24))
    ) {
        Column(modifier = Modifier.fillMaxSize()) {

            // --- GÓRNY HEADER ---
            TopPaymentHeader(
                modifier = Modifier.background(Color(0xFF0A1A24))
            )

            // --- SEKCJA PRZYSTANKÓW ---
            StopsSection(
                boardingStop = boardingStop,
                alightingStop = alightingStop
            )

            Spacer(modifier = Modifier.weight(1f))
        }

        // --- LOGO NA DOLE ---
        BottomLogosBar(
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}