package com.rg.krg13_dev.utils

import android.content.Context
import android.util.Log
import java.io.File

fun saveBlackListToFile(context: Context, data: ByteArray) {
    try {
        val file = File(context.filesDir, "black_list.bin")

        file.outputStream().use { output ->
            output.write(data)
            output.flush()
        }

        Log.i(
            "BLACKLIST_FILE",
            "Zapisano blacklistę: ${file.absolutePath} (${data.size} bajtów)"
        )

    } catch (e: Exception) {
        Log.e("BLACKLIST_FILE", "Błąd zapisu blacklisty", e)
    }
}
