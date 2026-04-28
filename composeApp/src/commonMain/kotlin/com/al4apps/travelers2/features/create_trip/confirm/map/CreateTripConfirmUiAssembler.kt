package com.al4apps.travelers2.features.create_trip.confirm.map

import com.al4apps.travelers2.core.model.toDisplayString
import com.al4apps.travelers2.core.ui.UiText
import com.al4apps.travelers2.design_system.RouteTimelinePoint
import com.al4apps.travelers2.features.create_trip.confirm.model.CreateTripConfirmUiState
import com.al4apps.travelers2.features.create_trip.confirm.model.CreateTripConfirmVmState
import com.al4apps.travelers2.features.create_trip.model.TripTimeFormatter
import com.al4apps.travelers2.features.create_trip.model.Vehicle
import com.al4apps.travelers2.shared_model.ButtonUiState
import com.al4apps.travelers2.shared_model.HomeTopBarUiState
import travelers2.composeapp.generated.resources.Res
import travelers2.composeapp.generated.resources.create_trip_confirm_baggage
import travelers2.composeapp.generated.resources.create_trip_confirm_conditions_label
import travelers2.composeapp.generated.resources.create_trip_confirm_hint
import travelers2.composeapp.generated.resources.create_trip_confirm_parcel
import travelers2.composeapp.generated.resources.create_trip_confirm_price_value
import travelers2.composeapp.generated.resources.create_trip_confirm_publish
import travelers2.composeapp.generated.resources.create_trip_confirm_route_label
import travelers2.composeapp.generated.resources.create_trip_confirm_seats_value
import travelers2.composeapp.generated.resources.create_trip_confirm_title
import travelers2.composeapp.generated.resources.create_trip_confirm_vehicle_label
import travelers2.composeapp.generated.resources.ic_arrow_back

class CreateTripConfirmUiAssembler {

    fun assemble(
        vmState: CreateTripConfirmVmState,
        vehicle: Vehicle?,
    ): CreateTripConfirmUiState {
        val draft = vmState.draft
        val departureTimeText = draft.departureTime
            ?.let(TripTimeFormatter::toDisplayString)
            .orEmpty()
        val arrivalTimeText = draft.arrivalTime()
            ?.let(TripTimeFormatter::toDisplayString)

        return CreateTripConfirmUiState(
            topBarState = HomeTopBarUiState(
                title = UiText.Resource(Res.string.create_trip_confirm_title),
                isBackVisible = true,
                backIcon = Res.drawable.ic_arrow_back,
            ),
            title = UiText.Resource(Res.string.create_trip_confirm_title),
            driverName = vmState.driverName,
            driverMeta = "★ ${vmState.driverRating} • ${vmState.driverTripsText}",
            routeLabel = UiText.Resource(Res.string.create_trip_confirm_route_label),
            departure = RouteTimelinePoint(
                time = departureTimeText.takeIf { it.isNotEmpty() },
                title = draft.fromAddress?.toDisplayString().orEmpty(),
                subtitle = draft.meetingPoint.takeIf { it.isNotBlank() },
            ),
            arrival = RouteTimelinePoint(
                time = arrivalTimeText,
                title = draft.toAddress?.toDisplayString().orEmpty(),
                subtitle = null,
            ),
            vehicleLabel = UiText.Resource(Res.string.create_trip_confirm_vehicle_label),
            vehicleModel = vehicle?.model.orEmpty(),
            vehiclePlate = vehicle?.plateNumber.orEmpty(),
            conditionsLabel = UiText.Resource(Res.string.create_trip_confirm_conditions_label),
            seatsValue = UiText.Resource(
                value = Res.string.create_trip_confirm_seats_value,
                args = listOf(draft.freeSeatsCount),
            ),
            priceValue = UiText.Resource(
                value = Res.string.create_trip_confirm_price_value,
                args = listOf(draft.pricePerSeat ?: 0),
            ),
            baggageLabel = UiText.Resource(Res.string.create_trip_confirm_baggage),
            parcelLabel = UiText.Resource(Res.string.create_trip_confirm_parcel),
            baggageEnabled = draft.baggageAllowed,
            parcelEnabled = draft.parcelDeliveryAllowed,
            hint = UiText.Resource(Res.string.create_trip_confirm_hint),
            publishButtonUiState = ButtonUiState(
                text = UiText.Resource(Res.string.create_trip_confirm_publish),
                enabled = !vmState.isPublishing,
            ),
            errorMessage = vmState.errorMessage,
        )
    }
}
