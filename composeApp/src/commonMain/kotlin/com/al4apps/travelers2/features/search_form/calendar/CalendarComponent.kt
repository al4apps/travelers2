package com.al4apps.travelers2.features.search_form.calendar

import com.al4apps.travelers2.features.search_form.calendar.model.CalendarUiState
import com.arkivanov.decompose.value.Value

interface CalendarComponent {
    val uiState: Value<CalendarUiState>

    fun onPreviousMonthClick()

    fun onNextMonthClick()

    fun onCloseClick()

    fun onDayClick(day: Int)
}
