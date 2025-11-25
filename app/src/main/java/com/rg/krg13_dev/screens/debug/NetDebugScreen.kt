package com.rg.krg13_dev.screens.debug

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.rg.krg13_dev.utils.NetDebug

@Composable
fun NetDebugScreen() {

    var info by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        info = NetDebug.getAllNetworkInfo()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(20.dp),
        contentAlignment = Alignment.TopStart
    ) {
        Column {
            Text("NETWORK DEBUG", color = Color.Yellow)

            Spacer(modifier = Modifier.height(20.dp))

            Text(info, color = Color.White)
        }
    }
}
