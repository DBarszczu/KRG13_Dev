package com.rg.krg13_dev.utils

import java.io.File

object NetDebug {

    fun getAllNetworkInfo(): String {
        val builder = StringBuilder()

        try {
            val devFile = File("/proc/net/dev")

            if (!devFile.exists()) {
                builder.append("Brak pliku /proc/net/dev\n")
                return builder.toString()
            }

            builder.append("Interfejsy z /proc/net/dev:\n\n")

            val lines = devFile.readLines()

            for (line in lines.drop(2)) { // pomijamy nagłówki
                val parts = line.split(":")
                if (parts.size >= 2) {
                    val iface = parts[0].trim()
                    builder.append("Interfejs: $iface\n")
                }
            }

        } catch (e: Exception) {
            builder.append("Błąd: ${e.message}")
        }

        return builder.toString()
    }
}
