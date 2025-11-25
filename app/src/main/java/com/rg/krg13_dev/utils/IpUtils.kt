package com.rg.krg13_dev.utils

import java.io.BufferedReader
import java.io.InputStreamReader

object IpUtils {

    fun getDeviceIpByShell(): String {
        return try {
            val process = ProcessBuilder()
                .command("sh", "-c", "ip addr show")
                .redirectErrorStream(true)
                .start()

            val output = StringBuilder()
            BufferedReader(InputStreamReader(process.inputStream)).use { reader ->
                var line: String?
                while (reader.readLine().also { line = it } != null) {
                    output.append(line).append("\n")
                }
            }

            val text = output.toString().lowercase()

            // Szukamy wszystkich IPv4
            val regex = Regex("""inet (\d+\.\d+\.\d+\.\d+)""")
            val matches = regex.findAll(text)

            // Filtrujemy adresy prywatne
            matches.forEach { match ->
                val ip = match.groupValues[1]
                if (!ip.startsWith("127.") && !ip.startsWith("0.")) {
                    return ip
                }
            }

            "0.0.0.0"

        } catch (e: Exception) {
            "0.0.0.0"
        }
    }
}
