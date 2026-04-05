package com.al4apps.travelers2.core.settings

import com.al4apps.travelers2.core.platform.PlatformContext
import com.russhwolf.settings.SharedPreferencesSettings

actual class AppSettingsFactory actual constructor(
    private val platformContext: PlatformContext,
) {

    actual fun create(): AppSettings =
        AppSettingsImpl(
            settings = SharedPreferencesSettings.Factory(
                context = platformContext.applicationContext,
            ).create(name = "travelers_settings"),
        )
}
