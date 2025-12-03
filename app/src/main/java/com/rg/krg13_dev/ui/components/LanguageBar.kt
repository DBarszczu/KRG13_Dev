package com.rg.krg13_dev.ui.components

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.rg.krg13_dev.R
import com.rg.krg13_dev.utils.LocaleManager
import com.rg.krg13_dev.utils.SoundManager

@Composable
fun LanguageBar(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val activity = context as Activity

    val currentLanguage = LocaleManager.getSavedLanguage(context)

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Color(0xFF0A1A24))
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        LangIcon("pl", currentLanguage, R.drawable.flag_pl, activity)
        LangIcon("en", currentLanguage, R.drawable.flag_en, activity)
        LangIcon("de", currentLanguage, R.drawable.flag_de, activity)
        LangIcon("uk", currentLanguage, R.drawable.flag_uk, activity)
    }
}

@Composable
private fun LangIcon(
    lang: String,
    current: String,
    icon: Int,
    activity: Activity
) {
    val isSelected = lang == current

    Image(
        painter = painterResource(icon),
        contentDescription = "language_$lang",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .width(110.dp)
            .height(70.dp)
            .clip(RoundedCornerShape(6.dp))
            .graphicsLayer {
                alpha = if (isSelected) 0.35f else 1f
            }
            .clickable {
                SoundManager.playClick()
                if (!isSelected) {
                    LocaleManager.changeLanguage(activity, lang)
                }
            }
    )
}
