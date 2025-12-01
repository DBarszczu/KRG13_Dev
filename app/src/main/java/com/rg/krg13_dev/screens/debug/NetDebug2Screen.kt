package com.rg.krg13_dev.screens.debug

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.rg.krg13_dev.utils.NetDebug2

@Composable
fun NetDebug2Screen() {

    var output by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        output = NetDebug2.getInterfacesFromIp()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp)
    ) {
        Text("INTERFACES (ip link)\n", color = Color.Yellow)
        Text(output, color = Color.White)
    }
}
