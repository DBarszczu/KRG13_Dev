package com.rg.krg13_dev.utils

import android.content.Context
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
        SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale.US)
            .format(Date(Build.TIME))

    // ------------------ ETHERNET ONLY ------------------

    fun getCommunicationProtocol(): String =
        "ETHERNET"

    fun getMacAddress(): String {
        val out = runShell("cat /sys/class/net/eth0/address")
        return out.trim().ifEmpty { "Unknown" }
    }

    fun getIp(): String {
        return parseIp(runShell("ip addr show eth0"))
    }

    fun getNetmask(): String {
        val out = runShell("ifconfig eth0")
        return parseNetmask(out)
    }

    fun getGateway(): String {
        val out = runShell("ip route show")
        val regex = Regex("""default via (\d+\.\d+\.\d+\.\d+)""")
        return regex.find(out)?.groupValues?.get(1) ?: "Unknown"
    }

    // ------------------ Parsowanie ------------------

    private fun parseIp(text: String): String {
        val regex = Regex("""inet (\d+\.\d+\.\d+\.\d+)""")
        return regex.find(text)?.groupValues?.get(1) ?: "0.0.0.0"
    }

    private fun parseNetmask(text: String): String {
        val regex = Regex("""netmask (\d+\.\d+\.\d+\.\d+)""")
        return regex.find(text)?.groupValues?.get(1) ?: "Unknown"
    }

    // ------------------ Shell ------------------

    internal fun runShell(cmd: String): String {
        return try {
            val process = ProcessBuilder()
                .command("sh", "-c", cmd)
                .redirectErrorStream(true)
                .start()

            BufferedReader(InputStreamReader(process.inputStream)).use {
                it.readText()
            }
        } catch (e: Exception) {
            ""
        }
    }
}
