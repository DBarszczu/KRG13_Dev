package com.rg.krg13_dev.utils

import android.content.Context
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.SoundPool
import com.rg.krg13_dev.R

object SoundManager {

    private var soundPool: SoundPool? = null
    private var soundClick = 0
    private var isLoaded = false

    fun init(context: Context) {
        if (soundPool != null) return

        val audioManager =
            context.getSystemService(Context.AUDIO_SERVICE) as AudioManager

        audioManager.setStreamVolume(
            AudioManager.STREAM_MUSIC,
            audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC),
            0
        )

        val attrs = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_MEDIA) // 🔥
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build()

        soundPool = SoundPool.Builder()
            .setMaxStreams(5)
            .setAudioAttributes(attrs)
            .build()

        soundClick = soundPool!!.load(context, R.raw.click, 1)

        soundPool!!.setOnLoadCompleteListener { _, _, _ ->
            isLoaded = true
        }
    }

    fun playClick() {
        if (isLoaded) {
            soundPool?.play(soundClick, 3.0f, 3.0f, 1, 0, 1f)
        }
    }
}
