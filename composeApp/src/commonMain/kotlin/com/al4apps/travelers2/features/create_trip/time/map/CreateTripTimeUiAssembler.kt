package com.al4apps.travelers2.features.create_trip.time.map

import com.al4apps.travelers2.core.ui.UiText
import com.al4apps.travelers2.features.create_trip.model.TripDuration
import com.al4apps.travelers2.features.create_trip.model.TripTimeFormatter
import com.al4apps.travelers2.features.create_trip.time.model.CreateTripTimeUiState
import com.al4apps.travelers2.features.create_trip.time.model.CreateTripTimeVmState
import com.al4apps.travelers2.shared_model.ButtonUiState
import com.al4apps.travelers2.shared_model.HomeTopBarUiState
import travelers2.composeapp.generated.resources.Res
import travelers2.composeapp.generated.resources.create_trip_continue
import travelers2.composeapp.generated.resources.create_trip_time_arrival
import travelers2.composeapp.generated.resources.create_trip_time_arrival_unknown
import travelers2.composeapp.generated.resources.create_trip_time_departure
import travelers2.composeapp.generated.resources.create_trip_time_departure_placeholder
import travelers2.composeapp.generated.resources.create_trip_time_duration
import travelers2.composeapp.generated.resources.create_trip_time_duration_hours
import travelers2.composeapp.generated.resources.create_trip_time_duration_minutes
import travelers2.composeapp.generated.resources.create_trip_time_duration_optional
import travelers2.composeapp.generated.resources.create_trip_time_meeting
import travelers2.composeapp.generated.resources.create_trip_time_meeting_placeholder
import travelers2.composeapp.generated.resources.create_trip_time_subtitle
import travelers2.composeapp.generated.resources.create_trip_time_title
import travelers2.composeapp.generated.resources.create_trip_time_topbar_title
import travelers2.composeapp.generated.resources.ic_arrow_back

class CreateTripTimeUiAssembler {

    fun assemble(vmState: CreateTripTimeVmState): CreateTripTimeUiState {
        val isValid = vmState.departureTime != null

        val arrivalText = if (vmState.departureTime != null && !vmState.duration.isZero) {
            val arrival = vmState.duration.applyTo(vmState.departureTime)
            UiText.Resource(
                value = Res.string.create_trip_time_arrival,
                args = listOf(TripTimeFormatter.toDisplayString(arrival)),
            )
        } else if (vmState.departureTime != null) {
            UiText.Resource(
                value = Res.string.create_trip_time_arrival,
                args = listOf(TripTimeFormatter.toDisplayString(vmState.departureTime)),
            )
        } else {
            UiText.Resource(Res.string.create_trip_time_arrival_unknown)
        }

        return CreateTripTimeUiState(
            topBarState = HomeTopBarUiState(
                title = UiText.Resource(Res.string.create_trip_time_topbar_title),
                isBackVisible = true,
                backIcon = Res.drawable.ic_arrow_back,
                isBackClickable = true,
            ),
            title = UiText.Resource(Res.string.create_trip_time_title),
            subtitle = UiText.Resource(Res.string.create_trip_time_subtitle),
            departureLabel = UiText.Resource(Res.string.create_trip_time_departure),
            departureValue = vmState.departureTime?.let(TripTimeFormatter::toDisplayString).orEmpty(),
            departurePlaceholder = UiText.Resource(Res.string.create_trip_time_departure_placeholder),
            durationLabel = UiText.Resource(Res.string.create_trip_time_duration),
            durationOptional = UiText.Resource(Res.string.create_trip_time_duration_optional),
            durationHoursLabel = UiText.Resource(Res.string.create_trip_time_duration_hours),
            durationMinutesLabel = UiText.Resource(Res.string.create_trip_time_duration_minutes),
            durationHoursValue = formatHours(vmState.duration.hours),
            durationMinutesValue = formatMinutes(vmState.duration.minutes),
            canDecrementHours = vmState.duration.hours > 0,
            canIncrementHours = vmState.duration.hours < TripDuration.MAX_HOURS,
            canDecrementMinutes = vmState.duration.minutes > 0,
            canIncrementMinutes = vmState.duration.minutes < MINUTES_INCREMENT_MAX,
            arrivalText = arrivalText,
            meetingLabel = UiText.Resource(Res.string.create_trip_time_meeting),
            meetingValue = vmState.meetingPoint,
            meetingPlaceholder = UiText.Resource(Res.string.create_trip_time_meeting_placeholder),
            continueButtonUiState = ButtonUiState(
                text = UiText.Resource(Res.string.create_trip_continue),
                enabled = isValid,
            ),
        )
    }

    private fun formatHours(hours: Int): String = "$hours ч"
    private fun formatMinutes(minutes: Int): String = "$minutes м"

    private companion object {
        const val MINUTES_INCREMENT_MAX = 55
    }
}
