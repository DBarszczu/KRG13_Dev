package com.rg.krg13_dev.autocomputer.parser.tariff

data class Tariff(
    val rows: List<TariffRow>,
    val columns: List<TariffColumn>,
    val cells: List<TariffCell>
) {
    val colCount get() = columns.size

    fun getCell(rowIndex: Int, colIndex: Int): TariffCell? {
        val index = rowIndex * colCount + colIndex
        return cells.getOrNull(index)
    }

    fun bankRow(): TariffRow? =
        rows.firstOrNull { it.isBank }

    fun bankPrices(): Map<TicketType, TariffCell> {
        val row = bankRow() ?: return emptyMap()
        val rowIndex = rows.indexOf(row)

        return columns.mapIndexedNotNull { colIndex, col ->
            val cell = getCell(rowIndex, colIndex)
            cell?.let { TicketType.fromColumn(colIndex) to it }
        }.toMap()
    }
}
