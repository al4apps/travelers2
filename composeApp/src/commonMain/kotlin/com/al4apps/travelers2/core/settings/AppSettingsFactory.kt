package com.al4apps.travelers2.core.settings

import com.al4apps.travelers2.core.platform.PlatformContext

expect class AppSettingsFactory(platformContext: PlatformContext) {
    fun create(): AppSettings
}
