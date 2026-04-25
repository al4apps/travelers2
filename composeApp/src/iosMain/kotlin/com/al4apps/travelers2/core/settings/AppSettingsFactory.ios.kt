package com.al4apps.travelers2.core.settings

import com.russhwolf.settings.NSUserDefaultsSettings

actual class AppSettingsFactory {

    actual fun create(): AppSettings =
        AppSettingsImpl(
            settings = NSUserDefaultsSettings.Factory().create(name = "travelers_settings"),
        )
}
