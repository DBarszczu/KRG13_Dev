package com.rg.krg13_dev.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TicketControlLockOverlay() {

    // 🔒 animacja tylko ikonki kłódki
    val transition = rememberInfiniteTransition()
    val scale by transition.animateFloat(
        initialValue = 0.9f,
        targetValue = 1.1f,
        animationSpec = infiniteRepeatable(
            tween(
                durationMillis = 900,
                easing = FastOutSlowInEasing
            ),
            RepeatMode.Reverse
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xAA000000)), // ciemne półprzezroczyste tło
        contentAlignment = Alignment.Center
    ) {

        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            // 🔒 kłódka z delikatnym pulsem
            Text(
                text = "🔒",
                fontSize = (120 * scale).sp,
                color = Color(0xFFFFD740) // żółty
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Zablokowany",
                color = Color.White,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Kontrola Biletów",
                color = Color(0xFFEEEEEE),
                fontSize = 22.sp
            )
        }
    }
}
