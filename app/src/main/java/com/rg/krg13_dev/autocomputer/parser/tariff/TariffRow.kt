package com.rg.krg13_dev.autocomputer.parser.tariff

data class TariffRow(
    val index: Int,
    val zoneFrom: Int,
    val zoneTo: Int,
    val flags: Int
) {
    val isBank: Boolean get() = (flags and 0x04) != 0
    val isCity: Boolean get() = (flags and 0x01) != 0
}
