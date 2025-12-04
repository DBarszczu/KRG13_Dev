package com.rg.krg13_dev.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.rg.krg13_dev.R
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun TopPaymentHeader(
    modifier: Modifier = Modifier
) {
    // ZEGAR
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

    Row(
        modifier = modifier
            .fillMaxWidth()
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
}
