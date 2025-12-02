package com.rg.krg13_dev.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.rg.krg13_dev.R
import com.rg.krg13_dev.autocomputer.AutoComputerViewModel
import com.rg.krg13_dev.navigation.Screen
import com.rg.krg13_dev.ui.components.NoCommunicationOverlay
import com.rg.krg13_dev.ui.components.TicketButton
import com.rg.krg13_dev.ui.components.TicketButtonArrow
import com.rg.krg13_dev.ui.components.TicketControlLockOverlay
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun HomeScreen(
    navController: NavHostController,
    autoComputerViewModel: AutoComputerViewModel = viewModel()
) {
    // ------------------- ODBIÓR STANÓW -------------------
    val isConnected by autoComputerViewModel.isConnected.collectAsState()
    val isLocked by autoComputerViewModel.isLocked.collectAsState()
    val course by autoComputerViewModel.courseParams.collectAsState()

    // Dane z SETJ (z fallbackami)
    val boardingStop = course?.T_boardingStopName?.ifBlank { "-" } ?: "-"
    val alightingStop = course?.t_alightingStopName?.ifBlank { "-" } ?: "-"

    // ------------------- ZEGAR -------------------
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

    // ------------------- UI GŁÓWNY -------------------
    Box(modifier = Modifier.fillMaxSize()) {

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color(0xFF0A1A24)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {

                // ------------------- GÓRNY PASEK -------------------
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFF0A1A24))
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(R.drawable.visa),
                            contentDescription = null,
                            modifier = Modifier.size(50.dp)
                        )
                        Spacer(Modifier.width(8.dp))
                        Image(
                            painter = painterResource(R.drawable.mastercard),
                            contentDescription = null,
                            modifier = Modifier.size(50.dp)
                        )
                    }

                    Column(horizontalAlignment = Alignment.End) {
                        Text(
                            text = time,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFAFE1FF)
                        )
                        Text(
                            text = date,
                            fontSize = 16.sp,
                            color = Color.White
                        )
                    }
                }

                // ------------------- SEKCJA PRZYSTANKÓW -------------------
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFF0B2331))
                        .padding(16.dp)
                ) {
                    Spacer(Modifier.height(6.dp))

                    Text(
                        "PRZYSTANEK WEJŚCIOWY:",
                        color = Color(0xFF8FB4D8),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        boardingStop,
                        color = Color.White,
                        fontSize = 18.sp
                    )

                    Spacer(Modifier.height(12.dp))

                    Text(
                        "PRZYSTANEK WYJŚCIOWY:",
                        color = Color(0xFF8FB4D8),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        alightingStop,
                        color = Color.White,
                        fontSize = 18.sp
                    )
                }

                Spacer(Modifier.height(16.dp))

                // ------------------- KAFLE BILETÓW -------------------
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {

                    TicketButton(
                        title = "BILET NORMALNY",
                        price = "3.20 zł"
                    ) {
                        // TODO klik
                    }

                    Spacer(Modifier.height(12.dp))

                    TicketButton(
                        title = "BILET NORMALNY",
                        price = "1.60 zł"
                    ) {
                        // TODO klik
                    }

                    Spacer(Modifier.height(12.dp))

                    TicketButtonArrow(
                        title = "ZAKUP WIELU BILETÓW"
                    ) {
                        navController.navigate(Screen.MultiTicket.route)
                    }

                }

                Spacer(Modifier.weight(1f))
            }
        }

        // ------------------- OVERLAY: BRAK KOMUNIKACJI -------------------
        if (!isConnected) {
            NoCommunicationOverlay()
        }

        // ------------------- OVERLAY: KONTROLA BILETÓW -------------------
        if (isLocked) {
            TicketControlLockOverlay()
        }
    }
}
