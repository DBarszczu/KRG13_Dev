package com.rg.krg13_dev.screens.deviceInfo

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rg.krg13_dev.utils.SystemInfo
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeviceInfoScreen(onContinue: () -> Unit) {

    val context = LocalContext.current

    var hold by remember { mutableStateOf(false) }

    val appVersion = remember { SystemInfo.getAppVersion(context) }
    val firmware = remember { SystemInfo.getFirmwareVersion() }
    val coreDate = remember { SystemInfo.getCoreDate() }
    val buildId = remember { SystemInfo.getBuildId() }
    val androidVersion = remember { SystemInfo.getAndroidVersion() }

    val protocol = remember { SystemInfo.getCommunicationProtocol() }
    val mac = remember { SystemInfo.getMacAddress() }
    val ip = remember { SystemInfo.getIp() }
    val netmask = remember { SystemInfo.getNetmask() }
    val gateway = remember { SystemInfo.getGateway() }

    LaunchedEffect(hold) {
        if (!hold) {
            delay(3000)
            onContinue()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Device Info", fontSize = 26.sp) }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            ElevatedCard(
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.medium
            ) {

                Column(modifier = Modifier.padding(20.dp)) {

                    InfoItem("Application version", appVersion)

                    InfoItem("Firmware version", firmware)
                    InfoItem("Compilation date/time", coreDate)
                    InfoItem("Communication protocol", protocol)
                    InfoItem("Device address (MAC)", mac)

                    Spacer(Modifier.height(16.dp))

                    Text(
                        "Ethernet configuration",
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontSize = 26.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )

                    Spacer(Modifier.height(8.dp))

                    Text("IP address: $ip")
                    Text("Netmask: $netmask")
                    Text("Gateway: $gateway")
                    Text("FTP IP: Unknown")
                    Text("TCP port: Unknown")

                    Spacer(Modifier.height(16.dp))

                    InfoItem("Core date", coreDate)
                    InfoItem("Android TS version", androidVersion)
                    InfoItem("Build ID", buildId)
                }
            }

            Spacer(Modifier.height(24.dp))

            Button(
                onClick = { if (!hold) hold = true else onContinue() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    if (!hold) "HOLD" else "RELEASE",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
private fun InfoItem(label: String, value: String) {
    Column(modifier = Modifier.padding(vertical = 4.dp)) {
        Text(label, style = MaterialTheme.typography.bodyMedium)
        Text(
            value,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
    }
}
