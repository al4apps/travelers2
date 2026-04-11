package com.al4apps.travelers2.features.search_trips.calendar.model

import com.al4apps.travelers2.core.ui.UiText

data class CalendarUiState(
    val year: Int,
    val monthTitle: UiText,
    val weekdays: List<UiText>,
    val days: List<CalendarDayUiState>,
    val canGoPreviousMonth: Boolean,
)
