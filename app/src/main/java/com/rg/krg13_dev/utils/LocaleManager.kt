package com.rg.krg13_dev.utils

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.res.Configuration
import java.util.Locale

object LocaleManager {

    private const val PREFS = "app_lang"
    private const val KEY = "lang"

    // ✔ Pobierz zapisany język
    fun getSavedLanguage(context: Context): String {
        val prefs = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
        return prefs.getString(KEY, "pl") ?: "pl"
    }

    // ✔ Zapisz język
    fun saveLanguage(context: Context, lang: String) {
        context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
            .edit()
            .putString(KEY, lang)
            .apply()
    }

    // ✔ Owiń kontekst nowym językiem
    fun wrapContext(context: Context): ContextWrapper {
        val lang = getSavedLanguage(context)
        val locale = Locale.forLanguageTag(lang)
        Locale.setDefault(locale)

        val config = Configuration(context.resources.configuration)
        config.setLocale(locale)

        val newContext = context.createConfigurationContext(config)
        return ContextWrapper(newContext)
    }

    // ✔ Zmień język i odśwież aktywność
    fun changeLanguage(activity: Activity, lang: String) {
        saveLanguage(activity, lang)
        activity.recreate()
    }
}
