package com.rg.krg13_dev.utils

import java.io.BufferedReader
import java.io.InputStreamReader

object NetDebug2 {

    fun getInterfacesFromIp(): String {
        return try {
            val process = ProcessBuilder()
                .command("sh", "-c", "ip -o link show")
                .redirectErrorStream(true)
                .start()

            BufferedReader(InputStreamReader(process.inputStream)).use { it.readText() }

        } catch (e: Exception) {
            "Błąd: ${e.message}"
        }
    }
}
