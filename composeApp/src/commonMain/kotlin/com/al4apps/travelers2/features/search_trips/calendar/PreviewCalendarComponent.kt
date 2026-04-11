package com.al4apps.travelers2.features.search_trips.calendar

import com.al4apps.travelers2.features.search_trips.calendar.map.CalendarUiAssembler
import com.al4apps.travelers2.features.search_trips.calendar.model.CalendarUiState
import com.al4apps.travelers2.features.search_trips.calendar.model.CalendarVmState
import com.al4apps.travelers2.features.search_trips.model.CalendarDate
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value

class PreviewCalendarComponent : CalendarComponent {
    override val uiState: Value<CalendarUiState> = MutableValue(
        CalendarUiAssembler().assemble(
            CalendarVmState(
                displayedYear = 2026,
                displayedMonth = 4,
                selectedDate = CalendarDate(year = 2026, month = 4, day = 20),
                today = CalendarDate(year = 2026, month = 4, day = 11),
            ),
        )
    )

    override fun onPreviousMonthClick() = Unit

    override fun onNextMonthClick() = Unit

    override fun onCloseClick() = Unit

    override fun onDayClick(day: Int) = Unit
}
