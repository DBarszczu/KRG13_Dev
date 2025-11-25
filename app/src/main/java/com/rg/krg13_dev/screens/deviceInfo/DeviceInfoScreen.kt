package com.rg.krg13_dev.screens.deviceInfo

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rg.krg13_dev.utils.SystemInfo
import kotlinx.coroutines.delay

@Composable
fun DeviceInfoScreen(onContinue: () -> Unit) {

    val context = LocalContext.current

    var hold by remember { mutableStateOf(false) }

    val appVersion = remember { SystemInfo.getAppVersion(context) }
    val firmware = remember { SystemInfo.getFirmwareVersion() }
    val coreDate = remember { SystemInfo.getCoreDate() }
    val buildId = remember { SystemInfo.getBuildId() }
    val androidVersion = remember { SystemInfo.getAndroidVersion() }

    val protocol = remember { SystemInfo.getCommunicationProtocol(context) }
    val mac = remember { SystemInfo.getMacAddress() }
    val ip = remember { SystemInfo.getIp(context) }
    val netmask = remember { SystemInfo.getNetmask(context) }
    val gateway = remember { SystemInfo.getGateway(context) }

    LaunchedEffect(hold) {
        if (!hold) {
            delay(5000)
            onContinue()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEAEAEA)),
        contentAlignment = Alignment.Center
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .border(2.dp, Color.Gray, RoundedCornerShape(4.dp))
                .padding(16.dp)
        ) {
            Text("Firmware version", fontSize = 16.sp)
            Text(firmware, fontSize = 20.sp, fontWeight = FontWeight.Bold)

            Spacer(Modifier.height(12.dp))

            Text("Compilation date/time", fontSize = 16.sp)
            Text(coreDate, fontSize = 18.sp, fontWeight = FontWeight.Bold)

            Spacer(Modifier.height(12.dp))

            Text("Communication protocol", fontSize = 16.sp)
            Text(protocol, fontSize = 18.sp, fontWeight = FontWeight.Bold)

            Spacer(Modifier.height(12.dp))

            Text("Device address", fontSize = 16.sp)
            Text(mac, fontSize = 18.sp, fontWeight = FontWeight.Bold)

            Spacer(Modifier.height(16.dp))

            Text("Ethernet configuration:", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Text("IP address: $ip")
            Text("netmask: $netmask")
            Text("gateway: $gateway")
            Text("FTP IP: Unknown")
            Text("TCP port: Unknown")

            Spacer(Modifier.height(16.dp))

            Text("Core date: $coreDate")
            Text("TS version: $androidVersion")
            Text("Build ID: $buildId")
        }

        // HOLD button
        Button(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 40.dp),
            onClick = {
                if (!hold) hold = true else onContinue()
            }
        ) {
            Text(
                if (!hold) "HOLD" else "RELEASE",
                fontSize = 20.sp
            )
        }
    }
}
