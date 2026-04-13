package com.al4apps.travelers2.features.search_trips.calendar

import com.al4apps.travelers2.core.CoroutineFeature
import com.al4apps.travelers2.core.CoroutineFeatureImpl
import com.al4apps.travelers2.core.StateStore
import com.al4apps.travelers2.core.platform.PlatformDate
import com.al4apps.travelers2.core.platform.currentPlatformDate
import com.al4apps.travelers2.core.model.TripDate
import com.al4apps.travelers2.core.toUiState
import com.al4apps.travelers2.features.search_trips.calendar.map.CalendarUiAssembler
import com.al4apps.travelers2.features.search_trips.calendar.map.isBefore
import com.al4apps.travelers2.features.search_trips.calendar.map.isBeforeMonth
import com.al4apps.travelers2.features.search_trips.calendar.model.CalendarUiState
import com.al4apps.travelers2.features.search_trips.calendar.model.CalendarVmState
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value

class DefaultCalendarComponent(
    componentContext: ComponentContext,
    initialDate: TripDate?,
    private val onDateSelected: (TripDate) -> Unit,
    private val onCloseClick: () -> Unit,
    private val uiAssembler: CalendarUiAssembler = CalendarUiAssembler(),
) : CalendarComponent,
    ComponentContext by componentContext,
    CoroutineFeature by CoroutineFeatureImpl(componentContext) {

    private val vmStateStore = StateStore(
        initialState = stateKeeper.consume(
            key = STATE_KEY,
            strategy = CalendarVmState.serializer(),
        ) ?: initialVmState(initialDate = initialDate),
    )

    override val uiState: Value<CalendarUiState> = vmStateStore.toUiState(
        scope = featureScope,
        assemble = uiAssembler::assemble,
    )

    init {
        stateKeeper.register(
            key = STATE_KEY,
            strategy = CalendarVmState.serializer(),
            supplier = vmStateStore::value,
        )
    }

    override fun onPreviousMonthClick() {
        vmStateStore.updateState { state ->
            val (candidateYear, candidateMonth) = decrementMonth(
                year = state.displayedYear,
                month = state.displayedMonth,
            )
            val isBeforeMonth = isBeforeMonth(
                year = candidateYear,
                month = candidateMonth,
                minYear = state.today.year,
                minMonth = state.today.month
            )
            if (isBeforeMonth) {
                state
            } else {
                state.copy(
                    displayedYear = candidateYear,
                    displayedMonth = candidateMonth,
                )
            }
        }
    }

    override fun onNextMonthClick() {
        vmStateStore.updateState { state ->
            val (nextYear, nextMonth) = incrementMonth(
                year = state.displayedYear,
                month = state.displayedMonth,
            )
            state.copy(
                displayedYear = nextYear,
                displayedMonth = nextMonth,
            )
        }
    }

    override fun onCloseClick() {
        onCloseClick.invoke()
    }

    override fun onDayClick(day: Int) {
        val state = vmStateStore.value
        val selected = TripDate(
            year = state.displayedYear,
            month = state.displayedMonth,
            day = day,
        )
        if (selected.isBefore(state.today)) return

        vmStateStore.updateState {
            it.copy(selectedDate = selected)
        }
        onDateSelected.invoke(selected)
    }

    private fun initialVmState(initialDate: TripDate?): CalendarVmState {
        val today = currentPlatformDate().toCalendarDate()
        val selectedDate = initialDate
        val initialDisplayedDate = when {
            selectedDate == null -> today
            selectedDate.isBefore(today) -> today
            else -> selectedDate
        }

        return CalendarVmState(
            displayedYear = initialDisplayedDate.year,
            displayedMonth = initialDisplayedDate.month,
            selectedDate = selectedDate?.takeUnless { it.isBefore(today) },
            today = today,
        )
    }

    private fun decrementMonth(year: Int, month: Int): Pair<Int, Int> =
        if (month == 1) {
            (year - 1) to 12
        } else {
            year to (month - 1)
        }

    private fun incrementMonth(year: Int, month: Int): Pair<Int, Int> =
        if (month == 12) {
            (year + 1) to 1
        } else {
            year to (month + 1)
        }

    private companion object {
        const val STATE_KEY = "calendar_vm_state"
    }
}

private fun PlatformDate.toCalendarDate(): TripDate =
    TripDate(
        year = year,
        month = month,
        day = day,
    )
