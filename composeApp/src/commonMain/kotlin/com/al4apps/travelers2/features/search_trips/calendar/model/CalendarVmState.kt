package com.al4apps.travelers2.features.search_trips.calendar.model

import com.al4apps.travelers2.features.search_trips.model.CalendarDate
import kotlinx.serialization.Serializable

@Serializable
data class CalendarVmState(
    val displayedYear: Int,
    val displayedMonth: Int,
    val selectedDate: CalendarDate?,
    val today: CalendarDate,
)
