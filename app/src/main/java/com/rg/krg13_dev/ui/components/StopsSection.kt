package com.rg.krg13_dev.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import androidx.compose.ui.unit.sp
import com.rg.krg13_dev.R

@Composable
fun StopsSection(
    boardingStop: String,
    alightingStop: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Color(0xFF0B2331))
            .padding(16.dp)
    ) {
        Spacer(Modifier.height(6.dp))

        Text(
            text = stringResource(R.string.entry_stop),
            color = Color(0xFF8FB4D8),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = boardingStop,
            color = Color.White,
            fontSize = 26.sp
        )

        Spacer(Modifier.height(12.dp))

        Text(
            text = stringResource(R.string.exit_stop),
            color = Color(0xFF8FB4D8),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = alightingStop,
            color = Color.White,
            fontSize = 26.sp
        )
    }
}
