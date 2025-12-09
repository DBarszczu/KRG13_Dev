package com.rg.krg13_dev.autocomputer.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rg.krg13_dev.autocomputer.AutoComputerViewModel
import com.rg.krg13_dev.autocomputer.tariff.TariffTable

@Composable
fun TariffScreen() {

    // ⬇️⬇️⬇️  TO JEST WŁAŚNIE TEN FRAGMENT KTÓREGO CHCIAŁEŚ ⬇️⬇️⬇️
    val viewModel: AutoComputerViewModel = viewModel()
    // ⬆️⬆️⬆️  PARAMETR JEST "POZA" SYGNATURĄ FUNKCJI, JAK NA ZRZUCIE ⬆️⬆️⬆️

    val tariff = viewModel.tariff.collectAsState().value

    if (tariff == null) {
        Text("Brak taryfy")
        return
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        item {
            Text("TARYFA KASOWNIK")
            Spacer(Modifier.height(12.dp))
            HeaderSection(tariff)
            Spacer(Modifier.height(12.dp))
            ColumnsSection(tariff)
            Spacer(Modifier.height(12.dp))
            RowsSection(tariff)
            Spacer(Modifier.height(12.dp))
            PricesTable(tariff)
            Spacer(Modifier.height(12.dp))
            ButtonsSection(tariff)
            Spacer(Modifier.height(12.dp))
            ConfigSection(tariff)
        }
    }
}

@Composable
private fun HeaderSection(t: TariffTable) {
    Text("CRC: ${t.header.crc}")
    Text("Len: ${t.header.len}")
    Text("Nagłówek: ${t.header.headerSize} B")
}

@Composable
private fun ColumnsSection(t: TariffTable) {
    Text("Kolumny:")
    t.columns.forEachIndexed { i, c ->
        Text("[$i] Nr=${c.nr}  Nazwa=${c.name}  Strefa=${c.strefaWazn}")
    }
}

@Composable
private fun RowsSection(t: TariffTable) {
    Text("Wiersze:")
    t.rows.forEachIndexed { i, r ->
        Text("[$i] Nr=${r.nr}  Lp=${r.lPrzystOd}  Td=${r.dzial}")
    }
}

@Composable
private fun PricesTable(t: TariffTable) {
    Text("TABLICA CEN:")

    t.rows.forEachIndexed { index, row ->
        Row(Modifier.fillMaxWidth()) {
            Text(
                "[${row.nr}] Lp=${row.lPrzystOd} Td=${row.dzial}",
                modifier = Modifier.weight(1f)
            )

            t.columns.forEachIndexed { colIndex, _ ->
                val cellIndex = index * t.columns.size + colIndex
                val cell = t.cells[cellIndex]

                Text(
                    "${cell.cena0}",
                    modifier = Modifier.weight(0.4f),
                    textAlign = TextAlign.End
                )
            }
        }
    }
}

@Composable
private fun ButtonsSection(t: TariffTable) {
    Text("Przyciski kasownika:")
    t.buttons.forEach {
        Text("Nr=${it.nr}  Typ='${it.rodzaj.toChar()}'  Param=${it.param}")
    }
}

@Composable
private fun ConfigSection(t: TariffTable) {
    val c = t.config
    Text("Konfiguracja:")
    Text("Błędne kasowania: ${c.lBrakuWykas}")
    Text("Przesiadka: ${c.czasPrzes}")
    Text("Max EP: ${c.maxKwotaEP}")
}
