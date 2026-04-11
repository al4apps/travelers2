package com.al4apps.travelers2.core.platform

import java.time.LocalDate

actual fun currentPlatformDate(): PlatformDate {
    val now = LocalDate.now()
    return PlatformDate(
        year = now.year,
        month = now.monthValue,
        day = now.dayOfMonth,
    )
}
