package com.rg.krg13_dev.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TicketControlLockOverlay(
    modifier: Modifier = Modifier
) {
    val transition = rememberInfiniteTransition()

    val scale by transition.animateFloat(
        initialValue = 0.9f,
        targetValue = 1.1f,
        animationSpec = infiniteRepeatable(
            tween(900, easing = FastOutSlowInEasing),
            RepeatMode.Reverse
        )
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .clickable(    // ⛔ BLOKADA KLIKNIĘĆ
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {}
            .background(Color(0xAA000000)),
        contentAlignment = Alignment.Center
    ) {

        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            Text(
                text = "🔒",
                fontSize = (180 * scale).sp,
                color = Color(0xFFFFD740)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "ZABLOKOWANY",
                color = Color.White,
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Kontrola Biletów",
                color = Color(0xFFEEEEEE),
                fontSize = 28.sp
            )
        }
    }
}
