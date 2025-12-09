package com.rg.krg13_dev.autocomputer.tariff

data class TariffTable(
    val header: Nagl,
    val rows: List<Wiersz>,
    val columns: List<Kolumna>,
    val cells: List<Komorka>,
    val buttons: List<Przycisk>,
    val config: Config
)

data class Nagl(
    val crc: Int,
    val len: Int,
    val headerSize: Int,
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

data class Kolumna(
    val nr: Int,
    val name: String,
    val strefaWazn: Int
)

data class Komorka(
    val id: Int,
    val kodTaryfy: Int,
    val cena0: Int
)

data class Przycisk(
    val nr: Int,
    val rodzaj: Int,
    val param: Int
)

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
