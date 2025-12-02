package com.rg.krg13_dev.screens.tickets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.rg.krg13_dev.R
import com.rg.krg13_dev.ui.components.TicketCounter
import com.rg.krg13_dev.utils.SoundManager
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun MultiTicketScreen(
    navController: NavHostController
) {
    // ====================== ILOŚCI BILETÓW ======================
    var normalCount by remember { mutableStateOf(0) }
    var reducedCount by remember { mutableStateOf(0) }

    // ====================== CENY ======================
    val priceNormal = 3.20
    val priceReduced = 1.60

    // ====================== SUMA ======================
    val totalPrice = remember(normalCount, reducedCount) {
        normalCount * priceNormal + reducedCount * priceReduced
    }

    // ====================== ZEGAR ======================
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

    // ====================== UI ======================
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0A1A24))
    ) {

        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            // =======================================================
            //                       HEADER
            // =======================================================
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
                        modifier = Modifier.size(80.dp)
                    )
                    Spacer(Modifier.width(8.dp))
                    Image(
                        painter = painterResource(R.drawable.mastercard),
                        contentDescription = null,
                        modifier = Modifier.size(80.dp)
                    )
                }

                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = time,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFAFE1FF)
                    )
                    Text(
                        text = date,
                        fontSize = 24.sp,
                        color = Color.White
                    )
                }
            }

            Spacer(Modifier.height(12.dp))

            // =======================================================
            //                       LISTA BILETÓW
            // =======================================================
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {

                // BILET NORMALNY
                TicketCounter(
                    label = "BILET NORMALNY",
                    price = String.format("%.2f zł", priceNormal),
                    count = normalCount,
                    onMinus = {
                        SoundManager.playClick()
                        if (normalCount > 0) normalCount--
                    },
                    onPlus = {
                        SoundManager.playClick()
                        normalCount++
                    }
                )

                Spacer(Modifier.height(16.dp))

                // BILET ULGOWY
                TicketCounter(
                    label = "BILET ULGOWY",
                    price = String.format("%.2f zł", priceReduced),
                    count = reducedCount,
                    onMinus = {
                        SoundManager.playClick()
                        if (reducedCount > 0) reducedCount--
                    },
                    onPlus = {
                        SoundManager.playClick()
                        reducedCount++
                    }
                )

                Spacer(Modifier.height(24.dp))

                // =======================================================
                //                       SUMA DO ZAPŁATY
                // =======================================================
                Text(
                    text = "DO ZAPŁATY:",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = String.format("%.2f zł", totalPrice),
                    color = Color(0xFFAFE1FF),
                    fontSize = 36.sp,
                    fontWeight = FontWeight.ExtraBold
                )

                Spacer(Modifier.height(24.dp))

                // =======================================================
                //                       PRZYCISKI POD SUMĄ
                // =======================================================
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    // WSTECZ
                    Button(
                        onClick = {
                            SoundManager.playClick()
                            navController.popBackStack()
                        },
                        modifier = Modifier
                            .weight(1f)
                            .height(70.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF3B4F63)
                        )
                    ) {
                        Text(
                            "‹ WSTECZ",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.White
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    // ZAKUP
                    Button(
                        onClick = {
                            SoundManager.playClick()
                            // TODO implement payment
                        },
                        modifier = Modifier
                            .weight(1f)
                            .height(70.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF577CA0)
                        )
                    ) {
                        Text(
                            "ZAKUP",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}
