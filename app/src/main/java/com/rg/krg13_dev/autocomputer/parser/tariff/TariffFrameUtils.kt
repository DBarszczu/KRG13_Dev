package com.rg.krg13_dev.autocomputer.parser.tariff

fun calcCrc8Sum(data: ByteArray): Int =
    (data.drop(1).sumOf { it.toUByte().toInt() } and 0xFF)

/**
 * Szuka początku taryfy w ramce UDP:
 * - HEADER_SIZE == 55
 * - LEN (u32be) pasuje do bufora
 * - CRC się zgadza
 */
fun findTariffStartSmart(frame: ByteArray): Int {
    for (start in 0 until frame.size - 60) {
        val headerSize = frame[start + 5].toInt() and 0xFF
        if (headerSize != 55) continue

        val slice = frame.copyOfRange(start, frame.size)
        val len = try {
            slice.u32be(1).toInt()
        } catch (_: Exception) {
            continue
        }

        if (len <= 0 || start + len > frame.size) continue

        val tariff = frame.copyOfRange(start, start + len)
        val expectedCrc = tariff[0].toInt() and 0xFF
        val calculated = calcCrc8Sum(tariff)

        if (expectedCrc == calculated) {
            return start
        }
    }
    return -1
}
