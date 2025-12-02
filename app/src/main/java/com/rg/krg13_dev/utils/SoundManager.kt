package com.rg.krg13_dev.utils

import android.content.Context
import android.media.AudioAttributes
import android.media.SoundPool

object SoundManager {

    private var soundPool: SoundPool? = null
    private var soundClick = 0
    private var isLoaded = false

    fun init(context: Context) {
        if (soundPool != null) return

        val attrs = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build()

        soundPool = SoundPool.Builder()
            .setMaxStreams(5)
            .setAudioAttributes(attrs)
            .build()

        soundClick = soundPool!!.load(context, com.rg.krg13_dev.R.raw.click, 1)

        soundPool!!.setOnLoadCompleteListener { _, _, _ ->
            isLoaded = true
        }
    }

    fun playClick() {
        if (isLoaded) {
            soundPool?.play(soundClick, 1f, 1f, 1, 0, 1f)
        }
    }
}
