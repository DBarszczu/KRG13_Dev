package com.rg.krg13_dev.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun FlagButton(icon: Int, onClick: () -> Unit) {
    Image(
        painter = painterResource(icon),
        contentDescription = null,
        modifier = Modifier
            .size(100.dp)
            .clickable { onClick() }
            .clip(RoundedCornerShape(15.dp))
    )
}
