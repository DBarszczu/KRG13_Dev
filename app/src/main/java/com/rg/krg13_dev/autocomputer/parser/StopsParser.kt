package com.rg.krg13_dev.autocomputer.parser

import android.util.Log
import java.net.DatagramPacket
import java.nio.charset.Charset

data class Stop(
    val stationId: Int,
    val postId: Int,
    val name: String
)

object StopsParser {
    private const val TAG = "UDP"

    fun parse(
        packet: DatagramPacket,
        charset: Charset = Charset.forName("cp852")
    ): List<Stop> {
        val raw = String(packet.data, 0, packet.length, charset)
            .trim('\u0000', '\r', '\n')
            .trimStart()

        Log.d(TAG, "Stop list (surowe): $raw")
        return parseRaw(raw)
    }

    private fun parseRaw(raw: String): List<Stop> {
        val list = mutableListOf<Stop>()
        var i = 0

        while (i + 8 <= raw.length) {
            try {
                // 1) stopId = pierwsze 4 znaki HEX
                val stopIdHex = raw.substring(i, i + 4).trim()
                val stopId = stopIdHex.toInt(16)

                // 2) postId = kolejne 4 znaki HEX
                val postIdHex = raw.substring(i + 4, i + 8).trim()
                val postId = postIdHex.toInt(16)

                // 3) nazwa przystanku: od pozycji i+8 do '#' lub '\u0000'
                val nameStart = i + 8
                val nameEnd = raw.indexOfAny(charArrayOf('#', '\u0000'), nameStart)
                    .takeIf { it != -1 } ?: break

                val name = raw.substring(nameStart, nameEnd)

                // 4) dodanie przystanku do listy
                list += Stop(stopId, postId, name)
                Log.d(TAG, "Stop[${list.size - 1}] stopId=0x$stopIdHex→$stopId, postId=0x$postIdHex→$postId, name=\"$name\"")

                // 5) przesunięcie na kolejny wpis
                i = nameEnd + 1

            } catch (e: Exception) {
                Log.e(TAG, "Błąd przy parsowaniu przystanku w pozycji $i: ${e.message}")
                break
            }
        }

        Log.d(TAG, "Łącznie sparsowano ${list.size} przystanków")
        return list
    }
}
