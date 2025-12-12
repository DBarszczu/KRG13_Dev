package com.rg.krg13_dev.autocomputer.parser.tariff

fun ByteArray.u8(i: Int): Int {
    if (i !in indices) {
        throw IndexOutOfBoundsException("u8 out of bounds: index=$i size=$size")
    }
    return this[i].toInt() and 0xFF
}

fun ByteArray.u16be(i: Int): Int {
    if (i + 1 !in indices) {
        throw IndexOutOfBoundsException("u16be out of bounds: index=$i size=$size")
    }
    return (u8(i) shl 8) or u8(i + 1)
}

fun ByteArray.u32be(i: Int): Long {
    if (i + 3 !in indices) {
        throw IndexOutOfBoundsException("u32be out of bounds: index=$i size=$size")
    }
    return (u8(i).toLong() shl 24) or
            (u8(i + 1).toLong() shl 16) or
            (u8(i + 2).toLong() shl 8) or
            (u8(i + 3).toLong())
}
