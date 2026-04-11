package com.al4apps.travelers2.features.search_trips.model

import kotlinx.serialization.Serializable

@Serializable
data class CalendarDate(
    val year: Int,
    val month: Int,
    val day: Int,
)
