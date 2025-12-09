package com.rg.krg13_dev.autocomputer.tariff

object TariffParser {

    private fun u16(b1: Byte, b2: Byte): Int =
        ((b1.toInt() and 0xFF) shl 8) or (b2.toInt() and 0xFF)

    private fun u32(b: ByteArray, i: Int): Int =
        ((b[i].toInt() and 0xFF) shl 24) or
                ((b[i + 1].toInt() and 0xFF) shl 16) or
                ((b[i + 2].toInt() and 0xFF) shl 8) or
                (b[i + 3].toInt() and 0xFF)

    fun parse(data: ByteArray): TariffTable {

        val header = parseHeader(data)

        val rows = parseRows(data, header)
        val cols = parseColumns(data, header)
        val cells = parseCells(data, header)
        val buttons = parseButtons(data, header)
        val config = parseConfig(data, header)

        return TariffTable(header, rows, cols, cells, buttons, config)
    }

    private fun parseHeader(b: ByteArray): Nagl {
        fun u16At(i: Int) = u16(b[i], b[i + 1])
        fun u32At(i: Int) = u32(b, i)

        return Nagl(
            crc = b[0].toUByte().toInt(),
            len = u32At(1),
            headerSize = b[5].toUByte().toInt(),

            wierszCnt = u16At(6),
            wierszSize = b[8].toUByte().toInt(),
            wierszOffset = u32At(9),

            kolumnaCnt = u16At(13),
            kolumnaSize = b[15].toUByte().toInt(),
            kolumnaOffset = u32At(16),

            komorkaCnt = u16At(20),
            komorkaSize = b[22].toUByte().toInt(),
            komorkaOffset = u32At(23),

            przyciskCnt = u16At(27),
            przyciskSize = b[29].toUByte().toInt(),
            przyciskOffset = u32At(30),

            wierszWybSpos = b[34].toUByte().toInt(),
            wierszWybCnt = u16At(35),
            wierszWybSize = b[37].toUByte().toInt(),
            wierszWybOffset = u32At(38),

            kolumnaWybSpos = b[42].toUByte().toInt(),
            kolumnaWybCnt = u16At(43),
            kolumnaWybSize = b[45].toUByte().toInt(),
            kolumnaWybOffset = u32At(46),

            configSize = b[50].toUByte().toInt(),
            configOffset = u32At(51)
        )
    }

    private fun parseRows(data: ByteArray, h: Nagl): List<Wiersz> =
        List(h.wierszCnt) { i ->
            val off = h.wierszOffset + i * h.wierszSize
            Wiersz(
                nr = u16(data[off], data[off + 1]),
                wazny = data[off + 2].toUByte().toInt(),
                czasPrzes = u16(data[off + 3], data[off + 4]),
                wierszPrzes = u16(data[off + 5], data[off + 6]),
                kwotaOd = u16(data[off + 7], data[off + 8]),
                strefaOd = data[off + 9].toUByte().toInt(),
                lPrzystOd = data[off + 10].toUByte().toInt(),
                dzial = data[off + 11].toUByte().toInt(),
                strefaDo = data[off + 12].toUByte().toInt()
            )
        }

    private fun parseColumns(data: ByteArray, h: Nagl): List<Kolumna> =
        List(h.kolumnaCnt) { i ->
            val off = h.kolumnaOffset + i * h.kolumnaSize
            Kolumna(
                nr = u16(data[off], data[off + 1]),
                name = data.copyOfRange(off + 2, off + 13)
                    .toString(Charsets.ISO_8859_1).trimEnd(),
                strefaWazn = data[off + 13].toUByte().toInt()
            )
        }

    private fun parseCells(data: ByteArray, h: Nagl): List<Komorka> =
        List(h.komorkaCnt) { i ->
            val off = h.komorkaOffset + i * h.komorkaSize
            Komorka(
                id = u16(data[off], data[off + 1]),
                kodTaryfy = u16(data[off + 2], data[off + 3]),
                cena0 = u16(data[off + 4], data[off + 5])
            )
        }

    private fun parseButtons(data: ByteArray, h: Nagl): List<Przycisk> =
        List(h.przyciskCnt) { i ->
            val off = h.przyciskOffset + i * h.przyciskSize
            Przycisk(
                nr = data[off].toUByte().toInt(),
                rodzaj = data[off + 1].toUByte().toInt(),
                param = u16(data[off + 2], data[off + 3])
            )
        }

    private fun parseConfig(data: ByteArray, h: Nagl): Config {
        val off = h.configOffset
        return Config(
            lBrakuWykas = data[off].toUByte().toInt(),
            czasPrzes = data[off + 1].toUByte().toInt(),
            maxKwotaEP = u16(data[off + 2], data[off + 3]),
            maxPrzesEP = data[off + 4].toUByte().toInt(),
            maxKwotaDebetuEP = u16(data[off + 5], data[off + 6]),
            waluta0 = u16(data[off + 7], data[off + 8]),
            waluta1 = u16(data[off + 9], data[off + 10]),
            bity = data[off + 11].toUByte().toInt()
        )
    }
}
