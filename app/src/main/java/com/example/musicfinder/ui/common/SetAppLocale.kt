package com.example.musicfinder.ui.common

import android.content.Context
import java.util.Locale

fun setAppLocale(context: Context, languageCode: String) {
    val locale = Locale(languageCode)
    Locale.setDefault(locale)

    val config = context.resources.configuration
    config.setLocale(locale)
    config.setLayoutDirection(locale)

    context.resources.updateConfiguration(config, context.resources.displayMetrics)

}
