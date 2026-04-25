package com.al4apps.travelers2.core.settings

import android.content.Context
import com.russhwolf.settings.SharedPreferencesSettings

actual class AppSettingsFactory(private val context: Context) {

    actual fun create(): AppSettings =
        AppSettingsImpl(
            settings = SharedPreferencesSettings.Factory(
                context = context,
            ).create(name = "travelers_settings"),
        )
}
