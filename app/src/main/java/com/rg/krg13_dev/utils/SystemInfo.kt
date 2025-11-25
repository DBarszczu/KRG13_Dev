package com.rg.krg13_dev.utils

import android.content.Context
import android.net.wifi.WifiManager
import android.os.Build
import java.io.BufferedReader
import java.io.InputStreamReader
import java.text.SimpleDateFormat
import java.util.*

object SystemInfo {

    fun getAppVersion(context: Context): String =
        VersionUtils.getAppVersion(context)

    fun getFirmwareVersion(): String =
        Build.DISPLAY ?: "Unknown"

    fun getBuildId(): String =
        Build.ID ?: "Unknown"

    fun getAndroidVersion(): String =
        Build.VERSION.RELEASE ?: "Unknown"

    fun getCoreDate(): String =
        SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale.US).format(Date(Build.TIME))

    fun getCommunicationProtocol(context: Context): String {
        val ip = IpUtils.getDeviceIpByShell()

        return when {
            ip.startsWith("192.") -> "WIFI"
            ip.startsWith("10.") -> "ETHERNET"
            ip.startsWith("100.") -> "LTE"
            else -> "UNKNOWN"
        }
    }

    fun getMacAddress(): String {
        val out = runShell("cat /sys/class/net/eth0/address")

        return when {
            out.contains("Permission denied") -> "Brak dostępu"
            out.trim().isEmpty() -> "Unknown"
            else -> out.trim()
        }
    }

    fun getGateway(context: Context): String {
        return try {
            val wifiManager = context.getSystemService(Context.WIFI_SERVICE) as WifiManager
            val dhcp = wifiManager.dhcpInfo
            intToIp(dhcp.gateway)
        } catch (e: Exception) {
            "Unknown"
        }
    }

    fun getNetmask(context: Context): String {
        return try {
            val wifiManager = context.getSystemService(Context.WIFI_SERVICE) as WifiManager
            val dhcp = wifiManager.dhcpInfo
            intToIp(dhcp.netmask)
        } catch (e: Exception) {
            "Unknown"
        }
    }

    fun getIp(context: Context): String = IpUtils.getDeviceIpByShell()

    private fun intToIp(ip: Int): String {
        return ((ip and 0xff).toString() + "." +
                (ip shr 8 and 0xff) + "." +
                (ip shr 16 and 0xff) + "." +
                (ip shr 24 and 0xff))
    }

    private fun runShell(cmd: String): String {
        return try {
            val process = ProcessBuilder()
                .command("sh", "-c", cmd)
                .redirectErrorStream(true)
                .start()

            BufferedReader(InputStreamReader(process.inputStream))
                .use { it.readText() }
        } catch (e: Exception) {
            ""
        }
    }
}
