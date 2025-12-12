package com.rg.krg13_dev.autocomputer.parser.tariff

data class TariffColumn(
    val index: Int,
    val id: Int,
    val name: String
)

enum class TicketType {
    NORMALNY,
    ULGOWY;

    companion object {
        fun fromColumn(colIndex: Int): TicketType =
            if (colIndex == 0) NORMALNY else ULGOWY
    }
}
