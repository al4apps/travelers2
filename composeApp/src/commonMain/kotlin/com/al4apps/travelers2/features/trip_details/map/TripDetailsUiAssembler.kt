package com.al4apps.travelers2.features.trip_details.map

import com.al4apps.travelers2.core.ui.UiText
import com.al4apps.travelers2.features.trip_details.model.TripDetailsOptionUiModel
import com.al4apps.travelers2.features.trip_details.model.TripDetailsSeatUiModel
import com.al4apps.travelers2.features.trip_details.model.TripDetailsUiState
import com.al4apps.travelers2.features.trip_details.model.TripDetailsVmState
import com.al4apps.travelers2.shared_model.HomeTopBarUiState
import travelers2.composeapp.generated.resources.Res
import travelers2.composeapp.generated.resources.car_white
import travelers2.composeapp.generated.resources.ic_arrow_back
import travelers2.composeapp.generated.resources.ic_folder
import travelers2.composeapp.generated.resources.ic_sdcard
import travelers2.composeapp.generated.resources.profile_circle
import travelers2.composeapp.generated.resources.trip_details_baggage_allowed
import travelers2.composeapp.generated.resources.trip_details_baggage_disallowed
import travelers2.composeapp.generated.resources.trip_details_book
import travelers2.composeapp.generated.resources.trip_details_parcel_allowed
import travelers2.composeapp.generated.resources.trip_details_parcel_disallowed
import travelers2.composeapp.generated.resources.trip_details_price_title
import travelers2.composeapp.generated.resources.trip_details_seats_left
import travelers2.composeapp.generated.resources.trip_details_title

class TripDetailsUiAssembler {

    fun assemble(vmState: TripDetailsVmState): TripDetailsUiState = TripDetailsUiState(
        topBarState = HomeTopBarUiState(
            title = UiText.Resource(
                value = Res.string.trip_details_title,
                args = listOf(vmState.tripDateText),
            ),
            backIcon = Res.drawable.ic_arrow_back,
            isBackClickable = true,
            isBackVisible = true,
        ),
        driverAvatarRes = Res.drawable.profile_circle,
        driverName = UiText.Plain(vmState.driver.name),
        driverTripsText = UiText.Plain("${vmState.driver.completedTripsCount}+ поездок"),
        departureTime = UiText.Plain(vmState.departurePoint.time),
        departureCity = UiText.Plain(vmState.departurePoint.city),
        departureDistrict = UiText.Plain(vmState.departurePoint.district),
        arrivalTime = UiText.Plain(vmState.arrivalPoint.time),
        arrivalCity = UiText.Plain(vmState.arrivalPoint.city),
        arrivalDistrict = UiText.Plain(vmState.arrivalPoint.district),
        carImageRes = Res.drawable.car_white,
        carModel = UiText.Plain(vmState.car.model),
        carPlateNumber = UiText.Plain(vmState.car.plateNumber),
        options = listOf(
            TripDetailsOptionUiModel(
                iconRes = Res.drawable.ic_sdcard,
                title = UiText.Resource(
                    value = if (vmState.baggageAllowed) {
                        Res.string.trip_details_baggage_allowed
                    } else {
                        Res.string.trip_details_baggage_disallowed
                    },
                ),
                isAvailable = vmState.baggageAllowed,
            ),
            TripDetailsOptionUiModel(
                iconRes = Res.drawable.ic_folder,
                title = UiText.Resource(
                    value = if (vmState.parcelDeliveryAllowed) {
                        Res.string.trip_details_parcel_allowed
                    } else {
                        Res.string.trip_details_parcel_disallowed
                    },
                ),
                isAvailable = vmState.parcelDeliveryAllowed,
            ),
        ),
        seatsTitle = UiText.Resource(
            value = Res.string.trip_details_seats_left,
            args = listOf(vmState.freeSeatsCount),
        ),
        seats = vmState.seats.map { seat ->
            TripDetailsSeatUiModel(
                id = seat.id,
                type = seat.type,
            )
        },
        priceTitle = UiText.Resource(Res.string.trip_details_price_title),
        priceValue = UiText.Plain(vmState.priceText),
        bookingButtonTitle = UiText.Resource(Res.string.trip_details_book),
    )
}
