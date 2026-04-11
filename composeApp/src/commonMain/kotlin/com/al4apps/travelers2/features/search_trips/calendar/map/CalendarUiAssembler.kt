package com.al4apps.travelers2.features.search_trips.calendar.map

import com.al4apps.travelers2.core.ui.UiText
import com.al4apps.travelers2.features.search_trips.calendar.model.CalendarDayUiState
import com.al4apps.travelers2.features.search_trips.calendar.model.CalendarMonth
import com.al4apps.travelers2.features.search_trips.calendar.model.CalendarUiState
import com.al4apps.travelers2.features.search_trips.calendar.model.CalendarVmState
import com.al4apps.travelers2.features.search_trips.calendar.model.CalendarWeekday
import com.al4apps.travelers2.features.search_trips.model.CalendarDate

class CalendarUiAssembler {
    fun assemble(vmState: CalendarVmState): CalendarUiState {
        val firstDayOffset = CalendarMath.dayOfWeekMondayBased(
            year = vmState.displayedYear,
            month = vmState.displayedMonth,
            day = 1,
        )
        val daysInMonth = CalendarMath.daysInMonth(
            year = vmState.displayedYear,
            month = vmState.displayedMonth,
        )
        val cells = MutableList(size = 42) {
            CalendarDayUiState(
                day = null,
                isEnabled = false,
                isSelected = false,
            )
        }

        for (day in 1..daysInMonth) {
            val date = CalendarDate(
                year = vmState.displayedYear,
                month = vmState.displayedMonth,
                day = day,
            )
            val index = firstDayOffset + day - 1
            cells[index] = CalendarDayUiState(
                day = day,
                isEnabled = !date.isBefore(vmState.today),
                isSelected = vmState.selectedDate == date,
            )
        }

        val previousMonth = decrementMonth(vmState.displayedYear, vmState.displayedMonth)
        val canGoPreviousMonth = !isBeforeMonth(
            year = previousMonth.first,
            month = previousMonth.second,
            minYear = vmState.today.year,
            minMonth = vmState.today.month,
        )

        return CalendarUiState(
            year = vmState.displayedYear,
            monthTitle = UiText.Plain(CalendarMonth.byNumber(vmState.displayedMonth).title),
            weekdays = weekdays(),
            days = cells,
            canGoPreviousMonth = canGoPreviousMonth,
        )
    }

    private fun weekdays(): List<UiText> = CalendarWeekday.entries.map { day ->
        UiText.Resource(day.titleRes)
    }

    private fun decrementMonth(year: Int, month: Int): Pair<Int, Int> =
        if (month == 1) {
            (year - 1) to 12
        } else {
            year to (month - 1)
        }
}
