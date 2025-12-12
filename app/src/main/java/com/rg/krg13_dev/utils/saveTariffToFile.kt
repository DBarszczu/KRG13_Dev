package com.rg.krg13_dev.utils

import android.content.Context
import android.util.Log
import java.io.File

fun saveTariffToFile(context: Context, data: ByteArray) {
    try {
        val file = File(context.filesDir, "tariff_table.bin")

        file.outputStream().use { output ->
            output.write(data)
            output.flush()
        }

        Log.i(
            "TARIFF_FILE",
            "Zapisano taryfę: ${file.absolutePath} (${data.size} bajtów)"
        )

    } catch (e: Exception) {
        Log.e("TARIFF_FILE", "Błąd zapisu taryfy", e)
    }
}
