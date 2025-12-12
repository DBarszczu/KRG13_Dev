package com.rg.krg13_dev.utils

import java.text.NumberFormat
import java.util.Locale

fun formatPrice(grosze: Int): String {
    val zl = grosze / 100.0
    val format = NumberFormat.getNumberInstance(Locale("pl", "PL")).apply {
        minimumFractionDigits = 2
        maximumFractionDigits = 2
    }
    return "${format.format(zl)} zł"
}
