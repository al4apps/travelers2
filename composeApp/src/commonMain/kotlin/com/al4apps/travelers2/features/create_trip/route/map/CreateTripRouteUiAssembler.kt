package com.al4apps.travelers2.features.create_trip.route.map

import com.al4apps.travelers2.core.model.TripDateFormatter
import com.al4apps.travelers2.core.model.toDisplayString
import com.al4apps.travelers2.core.ui.UiText
import com.al4apps.travelers2.design_system.RecentTripCardState
import com.al4apps.travelers2.features.create_trip.model.RecentTrip
import com.al4apps.travelers2.features.create_trip.model.RecentTripStatus
import com.al4apps.travelers2.features.create_trip.route.model.CreateTripRouteUiState
import com.al4apps.travelers2.features.create_trip.route.model.CreateTripRouteVmState
import com.al4apps.travelers2.shared_model.ButtonUiState
import com.al4apps.travelers2.shared_model.HomeTopBarUiState
import travelers2.composeapp.generated.resources.Res
import travelers2.composeapp.generated.resources.create_trip_continue
import travelers2.composeapp.generated.resources.create_trip_recent_empty
import travelers2.composeapp.generated.resources.create_trip_recent_repeat
import travelers2.composeapp.generated.resources.create_trip_recent_status_moderation
import travelers2.composeapp.generated.resources.create_trip_recent_status_published
import travelers2.composeapp.generated.resources.create_trip_recent_title
import travelers2.composeapp.generated.resources.create_trip_route_title
import travelers2.composeapp.generated.resources.search_trips_from
import travelers2.composeapp.generated.resources.search_trips_to
import travelers2.composeapp.generated.resources.search_trips_when

class CreateTripRouteUiAssembler {

    fun assemble(
        vmState: CreateTripRouteVmState,
        recentTrips: List<RecentTrip>,
    ): CreateTripRouteUiState {
        val isValid = vmState.fromAddress != null &&
            vmState.toAddress != null &&
            vmState.tripDate != null

        return CreateTripRouteUiState(
            topBarState = HomeTopBarUiState(
                title = UiText.Resource(Res.string.create_trip_route_title),
                isBackVisible = false,
            ),
            title = UiText.Resource(Res.string.create_trip_route_title),
            fromValue = vmState.fromAddress?.toDisplayString().orEmpty(),
            toValue = vmState.toAddress?.toDisplayString().orEmpty(),
            dateValue = vmState.tripDate?.let(TripDateFormatter::toDisplayString).orEmpty(),
            fromPlaceholder = UiText.Resource(Res.string.search_trips_from),
            toPlaceholder = UiText.Resource(Res.string.search_trips_to),
            datePlaceholder = UiText.Resource(Res.string.search_trips_when),
            continueButtonUiState = ButtonUiState(
                text = UiText.Resource(Res.string.create_trip_continue),
                enabled = isValid,
            ),
            recentSectionTitle = UiText.Resource(Res.string.create_trip_recent_title),
            recentEmptyText = UiText.Resource(Res.string.create_trip_recent_empty),
            recentRepeatLabel = UiText.Resource(Res.string.create_trip_recent_repeat),
            recentTrips = recentTrips.map { it.toCardState() },
        )
    }

    private fun RecentTrip.toCardState(): RecentTripCardState =
        RecentTripCardState(
            id = id,
            fromCity = fromAddress.toDisplayString(),
            toCity = toAddress.toDisplayString(),
            dateText = TripDateFormatter.toDisplayString(tripDate),
            statusLabel = when (status) {
                RecentTripStatus.ON_MODERATION ->
                    UiText.Resource(Res.string.create_trip_recent_status_moderation)
                RecentTripStatus.PUBLISHED ->
                    UiText.Resource(Res.string.create_trip_recent_status_published)
                RecentTripStatus.COMPLETED -> null
            },
            isOnModeration = status == RecentTripStatus.ON_MODERATION,
        )
}
