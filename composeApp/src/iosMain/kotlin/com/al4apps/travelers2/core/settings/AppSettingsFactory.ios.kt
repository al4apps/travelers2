package com.al4apps.travelers2.core.settings

import com.al4apps.travelers2.core.platform.PlatformContext
import com.russhwolf.settings.NSUserDefaultsSettings

actual class AppSettingsFactory actual constructor(
    @Suppress("UNUSED_PARAMETER")
    private val platformContext: PlatformContext,
) {

    actual fun create(): AppSettings =
        AppSettingsImpl(
            settings = NSUserDefaultsSettings.Factory().create(name = "travelers_settings"),
        )
}
