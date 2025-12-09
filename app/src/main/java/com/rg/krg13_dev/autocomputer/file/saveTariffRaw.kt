package com.rg.krg13_dev.autocomputer.file

import android.content.Context
import android.util.Log
import java.io.File

fun saveTariffRaw(context: Context, data: ByteArray) {
    try {
        val dir = File(context.filesDir, "tariffs_raw")
        if (!dir.exists()) dir.mkdirs()

        // zawsze ta sama nazwa:
        val file = File(dir, "tariff.bin")
        file.writeBytes(data)

        Log.d("TARIFF_RAW", "Zapisano taryfę do: ${file.absolutePath}")

    } catch (e: Exception) {
        Log.e("TARIFF_RAW", "Błąd zapisu taryfy: ${e.message}")
    }
}
