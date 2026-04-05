package com.al4apps.travelers2.core.settings

import com.russhwolf.settings.Settings

private const val KEY_FIRST_LAUNCH = "is_first_launch"

class AppSettingsImpl(
    private val settings: Settings,
) : AppSettings {

    override var isFirstLaunch: Boolean
        get() = settings.getBoolean(
            key = KEY_FIRST_LAUNCH,
            defaultValue = true,
        )
        set(value) {
            settings.putBoolean(
                key = KEY_FIRST_LAUNCH,
                value = value,
            )
        }
}
