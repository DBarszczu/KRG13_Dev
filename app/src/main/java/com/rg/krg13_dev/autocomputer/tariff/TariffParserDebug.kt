package com.rg.krg13_dev.autocomputer.tariff

import android.util.Log
import java.nio.charset.Charset

object TariffParserDebug {

    // -----------------------------
    //   Pomocnicze konwersje typów
    //   (BIG-ENDIAN jak w pliku TT)
    // -----------------------------
    private fun u16(b1: Byte, b2: Byte): Int =
        ((b1.toInt() and 0xFF) shl 8) or (b2.toInt() and 0xFF)

    private fun u32(b: ByteArray, i: Int): Int =
        ((b[i].toInt() and 0xFF) shl 24) or
                ((b[i + 1].toInt() and 0xFF) shl 16) or
                ((b[i + 2].toInt() and 0xFF) shl 8) or
                (b[i + 3].toInt() and 0xFF)


    // ---------------------------------------------------
    //  GŁÓWNA FUNKCJA – pełne parsowanie i wypisywanie TT
    //  UWAGA: data[] ma zaczynać się OD CRC TABELI TT,
    //         czyli od drugiego bajtu ramki UDP.
    // ---------------------------------------------------
    fun parseAndLog(data: ByteArray) {
        try {
            Log.d("TARIFF", "============= PARSING TARIFF TABLE ==============")
            Log.d("TARIFF", "Total bytes: ${data.size}")

            if (data.size < 55) {
                Log.e("TARIFF", "Za mało danych na nagłówek TT (min 55 bajtów)")
                return
            }

            val crc = data[0].toUByte().toInt()
            val len = u32(data, 1)
            val rozmNagl = data[5].toUByte().toInt()

            Log.d("TARIFF", "CRC: $crc")
            Log.d("TARIFF", "LEN (z nagłówka): $len")
            Log.d("TARIFF", "Header size (rozmNagl): $rozmNagl")

            if (rozmNagl > data.size) {
                Log.e(
                    "TARIFF",
                    "rozmNagl ($rozmNagl) > data.size (${data.size}) – nagłówek uszkodzony?"
                )
                return
            }

            val nagl = parseNagl(data)
            dumpNagl(nagl)

            // sanity check len
            if (nagl.len > data.size) {
                Log.w(
                    "TARIFF",
                    "LEN w nagłówku (${nagl.len}) > real data (${data.size}) – używam rozmiaru bufora"
                )
            }

            val rowOff = nagl.wierszOffset
            val colOff = nagl.kolumnaOffset
            val cellOff = nagl.komorkaOffset
            val btnOff = nagl.przyciskOffset
            val cfgOff = nagl.configOffset

            // --------------------
            //    WIERSZE TARYFY
            // --------------------
            Log.d("TARIFF", "=========== WIERSZE (${nagl.wierszCnt}) ===========")
            for (i in 0 until nagl.wierszCnt) {
                val base = rowOff + i * nagl.wierszSize
                if (base + nagl.wierszSize > data.size) {
                    Log.w("TARIFF", "Wiersz[$i] wychodzi poza bufor – przerywam")
                    break
                }
                val w = parseRow(data, base)
                Log.d("TARIFF", "Wiersz[$i] $w")
            }

            // --------------------
            //    KOLUMNY TARYFY
            // --------------------
            Log.d("TARIFF", "=========== KOLUMNY (${nagl.kolumnaCnt}) ==========")
            for (i in 0 until nagl.kolumnaCnt) {
                val base = colOff + i * nagl.kolumnaSize
                if (base + nagl.kolumnaSize > data.size) {
                    Log.w("TARIFF", "Kolumna[$i] wychodzi poza bufor – przerywam")
                    break
                }
                val k = parseColumn(data, base)
                Log.d("TARIFF", "Kolumna[$i] $k")
            }

            // --------------------
            //    KOMÓRKI CEN
            // --------------------
            Log.d("TARIFF", "=========== KOMÓRKI (${nagl.komorkaCnt}) ==========")
            for (i in 0 until nagl.komorkaCnt) {
                val base = cellOff + i * nagl.komorkaSize
                if (base + nagl.komorkaSize > data.size) {
                    Log.w("TARIFF", "Komórka[$i] wychodzi poza bufor – przerywam")
                    break
                }
                val c = parseCell(data, base, nagl.komorkaSize)
                Log.d("TARIFF", "Komórka[$i] $c")
            }

            // --------------------
            //    PRZYCISKI
            // --------------------
            Log.d("TARIFF", "=========== PRZYCISKI (${nagl.przyciskCnt}) =======")
            for (i in 0 until nagl.przyciskCnt) {
                val base = btnOff + i * nagl.przyciskSize
                if (base + nagl.przyciskSize > data.size) {
                    Log.w("TARIFF", "Przycisk[$i] wychodzi poza bufor – przerywam")
                    break
                }
                val b = parseButton(data, base)
                Log.d("TARIFF", "Przycisk[$i] $b")
            }

            // --------------------
            //    CONFIG
            // --------------------
            Log.d("TARIFF", "=========== CONFIG (${nagl.configSize} bytes) =====")
            if (nagl.configSize >= 12 && cfgOff + 12 <= data.size) {
                val config = parseConfig(data, cfgOff)
                dumpConfig(config)
            } else {
                Log.d("TARIFF", "Config: brak lub nieznany format (size=${nagl.configSize})")
            }

            Log.d("TARIFF", "============= END PARSING TARIFF ==============")

        } catch (e: Exception) {
            Log.e("TARIFF", "Parse error: ${e.message}", e)
        }
    }


    // -------------------------------------------------------
    //              STRUKTURY ODWZOROWANE 1:1 Z KRG
    // -------------------------------------------------------

    // -------- Nagłówek taryfy (sNaglTT) --------
    data class Nagl(
        val crc: Int,
        val len: Int,
        val rozmNagl: Int,

        val wierszCnt: Int,
        val wierszSize: Int,
        val wierszOffset: Int,

        val kolumnaCnt: Int,
        val kolumnaSize: Int,
        val kolumnaOffset: Int,

        val komorkaCnt: Int,
        val komorkaSize: Int,
        val komorkaOffset: Int,

        val przyciskCnt: Int,
        val przyciskSize: Int,
        val przyciskOffset: Int,

        val wierszWybSpos: Int,
        val wierszWybCnt: Int,
        val wierszWybSize: Int,
        val wierszWybOffset: Int,

        val kolumnaWybSpos: Int,
        val kolumnaWybCnt: Int,
        val kolumnaWybSize: Int,
        val kolumnaWybOffset: Int,

        val configSize: Int,
        val configOffset: Int
    )

    private fun parseNagl(b: ByteArray): Nagl {

        fun u16At(i: Int) = u16(b[i], b[i + 1])
        fun u32At(i: Int) = u32(b, i)

        return Nagl(
            crc = b[0].toUByte().toInt(),
            len = u32At(1),
            rozmNagl = b[5].toUByte().toInt(),

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

    private fun dumpNagl(n: Nagl) {
        Log.d("TARIFF", "===== NAGŁÓWEK TARYFY =====")
        Log.d("TARIFF", n.toString())
    }


    // -------- WIERSZ TARYFY (sWiersz) --------
    data class Wiersz(
        val nr: Int,
        val wazny: Int,
        val czasPrzes: Int,
        val wierszPrzes: Int,
        val kwotaOd: Int,
        val strefaOd: Int,
        val lPrzystOd: Int,
        val dzial: Int,
        val strefaDo: Int
    )

    private fun parseRow(b: ByteArray, off: Int): Wiersz {
        return Wiersz(
            nr = u16(b[off], b[off + 1]),
            wazny = b[off + 2].toUByte().toInt(),
            czasPrzes = u16(b[off + 3], b[off + 4]),
            wierszPrzes = u16(b[off + 5], b[off + 6]),
            kwotaOd = u16(b[off + 7], b[off + 8]),
            strefaOd = b[off + 9].toUByte().toInt(),
            lPrzystOd = b[off + 10].toUByte().toInt(),
            dzial = b[off + 11].toUByte().toInt(),
            strefaDo = b[off + 12].toUByte().toInt()
        )
    }


    // -------- KOLUMNA TARYFY (sKolumna) --------
    data class Kolumna(
        val nr: Int,
        val name: String,
        val strefaWazn: Int
    )

    private fun parseColumn(b: ByteArray, off: Int): Kolumna {
        val nr = u16(b[off], b[off + 1])
        val raw = b.copyOfRange(off + 2, off + 13)
        val name = raw.toString(Charset.forName("Windows-1250")).trim()
        val strefa = b[off + 13].toUByte().toInt()
        return Kolumna(nr, name, strefa)
    }


    // -------- KOMÓRKA TARYFY (sKomorka) --------
    data class Komorka(
        val id: Int,
        val kodTaryfy: Int,
        val cena0: Int,
        val cena1: Int?
    )

    private fun parseCell(b: ByteArray, off: Int, size: Int): Komorka {
        val id = u16(b[off], b[off + 1])
        val kod = u16(b[off + 2], b[off + 3])
        val cena0 = u16(b[off + 4], b[off + 5])
        // w Twoim pliku komorkaSize = 6, więc cena1 zwykle nie występuje
        val cena1 = if (size >= 8 && off + 7 < b.size) {
            u16(b[off + 6], b[off + 7])
        } else null
        return Komorka(id, kod, cena0, cena1)
    }


    // -------- PRZYCISK TARYFY (sPrzycisk) --------
    data class Przycisk(
        val nr: Int,
        val rodzaj: Int,
        val param: Int
    )

    private fun parseButton(b: ByteArray, off: Int): Przycisk {
        return Przycisk(
            nr = b[off].toUByte().toInt(),
            rodzaj = b[off + 1].toUByte().toInt(),
            param = u16(b[off + 2], b[off + 3])
        )
    }


    // -------- CONFIG TARYFY (sConfig) --------
    // U Ciebie configSize = 19, ale klasyczny sConfig ma 12 bajtów.
    // Bierzemy pierwsze 12 bajtów zgodnie z klasycznym sConfig.
    data class Config(
        val lBrakuWykas: Int,
        val czasPrzes: Int,
        val maxKwotaEP: Int,
        val maxPrzesEP: Int,
        val maxKwotaDebetuEP: Int,
        val waluta0: Int,
        val waluta1: Int,
        val bity: Int
    )

    private fun parseConfig(b: ByteArray, off: Int): Config {
        return Config(
            lBrakuWykas = b[off].toUByte().toInt(),
            czasPrzes = b[off + 1].toUByte().toInt(),
            maxKwotaEP = u16(b[off + 2], b[off + 3]),
            maxPrzesEP = b[off + 4].toUByte().toInt(),
            maxKwotaDebetuEP = u16(b[off + 5], b[off + 6]),
            waluta0 = u16(b[off + 7], b[off + 8]),
            waluta1 = u16(b[off + 9], b[off + 10]),
            bity = b[off + 11].toUByte().toInt()
        )
    }

    private fun dumpConfig(c: Config) {
        Log.d("TARIFF", "CONFIG: $c")
    }
}
