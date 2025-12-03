package com.rg.krg13_dev.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.rg.krg13_dev.R

@Composable
fun BottomLogosBar(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Color(0xFF060F13))
            .padding(vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Image(
            painter = painterResource(R.drawable.logo_zdik),
            contentDescription = "ZDIK",
            modifier = Modifier.width(50.dp)
        )

        Image(
            painter = painterResource(R.drawable.logo_mzk_tarnow),
            contentDescription = "MZK Tarnów",
            modifier = Modifier.width(150.dp)
        )

        Image(
            painter = painterResource(R.drawable.logo_wkt),
            contentDescription = "WKT",
            modifier = Modifier.width(50.dp)
        )
    }
}
