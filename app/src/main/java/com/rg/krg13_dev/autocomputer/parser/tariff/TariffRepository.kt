package com.rg.krg13_dev.autocomputer.parser.tariff

// parser/tariff/TariffRepository.kt
object TariffRepository {

    private var current: Tariff? = null

    fun update(tariff: Tariff) {
        current = tariff
    }

    fun getBankPrices(): Pair<Int, Int>? {
        val tariff = current ?: return null
        val prices = tariff.bankPrices()

        val normal = prices[TicketType.NORMALNY]?.priceGrosz
        val reduced = prices[TicketType.ULGOWY]?.priceGrosz

        if (normal != null && reduced != null) {
            return normal to reduced
        }
        return null
    }
}
