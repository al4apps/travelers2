package com.al4apps.travelers2.core.platform

data class PlatformDate(
    val year: Int,
    val month: Int,
    val day: Int,
)

expect fun currentPlatformDate(): PlatformDate
