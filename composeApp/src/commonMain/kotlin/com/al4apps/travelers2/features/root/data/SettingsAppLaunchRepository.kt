package com.al4apps.travelers2.features.root.data

import com.al4apps.travelers2.core.settings.AppSettings
import com.al4apps.travelers2.features.root.domain.repository.AppLaunchRepository

class SettingsAppLaunchRepository(
    private val appSettings: AppSettings,
) : AppLaunchRepository {

    override fun isFirstLaunch(): Boolean = appSettings.isFirstLaunch

    override fun completeFirstLaunch() {
        appSettings.isFirstLaunch = false
    }
}
