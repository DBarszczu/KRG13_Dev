package com.rg.krg13_dev.autocomputer.parser.tariff

object TariffParser {

    fun parse(tt: ByteArray): Tariff {
        val rowCount = tt.u16be(6)
        val rowSize = tt.u8(8)
        val rowOffset = tt.u32be(9).toInt()

        val colCount = tt.u16be(13)
        val colSize = tt.u8(15)
        val colOffset = tt.u32be(16).toInt()

        val cellCount = tt.u16be(20)
        val cellSize = tt.u8(22)
        val cellOffset = tt.u32be(23).toInt()

        val rows = (0 until rowCount).map { i ->
            val base = rowOffset + i * rowSize
            TariffRow(
                index = i,
                zoneFrom = tt.u8(base),
                zoneTo = tt.u8(base + 1),
                flags = tt.u8(base + 2)
            )
        }

        val columns = (0 until colCount).map { i ->
            val base = colOffset + i * colSize
            val id = tt.u16be(base)
            val name = tt.copyOfRange(base + 2, base + 13)
                .toString(Charsets.US_ASCII)
                .trim()
            TariffColumn(i, id, name)
        }

        val cells = (0 until cellCount).map { i ->
            val base = cellOffset + i * cellSize
            TariffCell(
                id = tt.u16be(base),
                priceGrosz = tt.u16be(base + 4)
            )
        }

        return Tariff(rows, columns, cells)
    }
}
