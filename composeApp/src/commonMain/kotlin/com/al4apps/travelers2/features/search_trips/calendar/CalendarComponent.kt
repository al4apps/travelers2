package com.al4apps.travelers2.features.search_trips.calendar

import com.al4apps.travelers2.features.search_trips.calendar.model.CalendarUiState
import com.arkivanov.decompose.value.Value

interface CalendarComponent {
    val uiState: Value<CalendarUiState>

    fun onPreviousMonthClick()

    fun onNextMonthClick()

    fun onCloseClick()

    fun onDayClick(day: Int)
}
