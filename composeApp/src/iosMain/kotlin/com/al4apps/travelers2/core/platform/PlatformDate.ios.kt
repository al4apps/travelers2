package com.al4apps.travelers2.core.platform

import platform.Foundation.NSDate
import platform.Foundation.NSDateFormatter
import platform.Foundation.NSLocale

actual fun currentPlatformDate(): PlatformDate {
    val formatter = NSDateFormatter().apply {
        dateFormat = "yyyy-MM-dd"
        locale = NSLocale(localeIdentifier = "en_US_POSIX")
    }
    val parts = formatter.stringFromDate(NSDate()).split("-")

    return PlatformDate(
        year = parts[0].toInt(),
        month = parts[1].toInt(),
        day = parts[2].toInt(),
    )
}
