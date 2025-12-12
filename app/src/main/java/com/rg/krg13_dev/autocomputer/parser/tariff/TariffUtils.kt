package com.rg.krg13_dev.autocomputer.parser.tariff

import android.util.Log

object TariffUtils {

    fun logBankPrices(tariff: Tariff) {
        Log.i("TARIFF_BANK", "---- CENY BILETÓW BANKOWYCH ----")

        val prices = tariff.bankPrices()
        if (prices.isEmpty()) {
            Log.w("TARIFF_BANK", "Brak taryfy bankowej")
            return
        }

        prices.forEach { (type, cell) ->
            Log.i(
                "TARIFF_BANK",
                "BANK [$type] → cellId=${cell.id} cena=${cell.priceZl} zł"
            )
        }
    }
}
