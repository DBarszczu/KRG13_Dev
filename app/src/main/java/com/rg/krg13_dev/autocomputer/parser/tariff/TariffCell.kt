package com.rg.krg13_dev.autocomputer.parser.tariff

data class TariffCell(
    val id: Int,
    val priceGrosz: Int
) {
    val priceZl: Double get() = priceGrosz / 100.0
}
