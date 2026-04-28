package com.al4apps.travelers2.features.create_trip.vehicle.map

import com.al4apps.travelers2.core.ui.UiText
import com.al4apps.travelers2.design_system.VehicleCarouselItemState
import com.al4apps.travelers2.features.create_trip.model.CreateTripDraft
import com.al4apps.travelers2.features.create_trip.vehicle.model.CreateTripVehicleUiState
import com.al4apps.travelers2.features.create_trip.vehicle.model.CreateTripVehicleVmState
import com.al4apps.travelers2.shared_model.ButtonUiState
import com.al4apps.travelers2.shared_model.HomeTopBarUiState
import travelers2.composeapp.generated.resources.Res
import travelers2.composeapp.generated.resources.create_trip_continue
import travelers2.composeapp.generated.resources.create_trip_vehicle_baggage
import travelers2.composeapp.generated.resources.create_trip_vehicle_extras
import travelers2.composeapp.generated.resources.create_trip_vehicle_parcel
import travelers2.composeapp.generated.resources.create_trip_vehicle_price_currency_short
import travelers2.composeapp.generated.resources.create_trip_vehicle_price_hint
import travelers2.composeapp.generated.resources.create_trip_vehicle_price_placeholder
import travelers2.composeapp.generated.resources.create_trip_vehicle_price_title
import travelers2.composeapp.generated.resources.create_trip_vehicle_seats_subtitle
import travelers2.composeapp.generated.resources.create_trip_vehicle_seats_title
import travelers2.composeapp.generated.resources.create_trip_vehicle_seats_unit
import travelers2.composeapp.generated.resources.create_trip_vehicle_section
import travelers2.composeapp.generated.resources.create_trip_vehicle_title
import travelers2.composeapp.generated.resources.ic_arrow_back

class CreateTripVehicleUiAssembler {

    fun assemble(vmState: CreateTripVehicleVmState): CreateTripVehicleUiState {
        val price = vmState.priceInput.toIntOrNull()
        val isValid = vmState.selectedVehicleId != null &&
            vmState.freeSeatsCount in CreateTripDraft.MIN_FREE_SEATS..CreateTripDraft.MAX_FREE_SEATS &&
            price != null && price > 0

        return CreateTripVehicleUiState(
            topBarState = HomeTopBarUiState(
                title = UiText.Resource(Res.string.create_trip_vehicle_title),
                isBackVisible = true,
                backIcon = Res.drawable.ic_arrow_back,
            ),
            isLoading = vmState.isLoading,
            errorMessage = vmState.errorMessage,
            sectionTitle = UiText.Resource(Res.string.create_trip_vehicle_section),
            vehicles = vmState.vehicles.map { vehicle ->
                VehicleCarouselItemState(
                    id = vehicle.id,
                    model = vehicle.model,
                    plateNumber = vehicle.plateNumber,
                    seatsLabel = "${vehicle.totalSeats} мест",
                    isSelected = vehicle.id == vmState.selectedVehicleId,
                )
            },
            seatsTitle = UiText.Resource(Res.string.create_trip_vehicle_seats_title),
            seatsSubtitle = UiText.Resource(Res.string.create_trip_vehicle_seats_subtitle),
            seatsValue = vmState.freeSeatsCount.toString(),
            canDecrementSeats = vmState.freeSeatsCount > CreateTripDraft.MIN_FREE_SEATS,
            canIncrementSeats = vmState.freeSeatsCount < CreateTripDraft.MAX_FREE_SEATS,
            priceTitle = UiText.Resource(Res.string.create_trip_vehicle_price_title),
            priceInput = vmState.priceInput,
            priceCurrency = UiText.Resource(Res.string.create_trip_vehicle_price_currency_short),
            priceHint = UiText.Resource(Res.string.create_trip_vehicle_price_hint),
            pricePlaceholder = UiText.Resource(Res.string.create_trip_vehicle_price_placeholder),
            extrasTitle = UiText.Resource(Res.string.create_trip_vehicle_extras),
            baggageLabel = UiText.Resource(Res.string.create_trip_vehicle_baggage),
            parcelLabel = UiText.Resource(Res.string.create_trip_vehicle_parcel),
            baggageAllowed = vmState.baggageAllowed,
            parcelAllowed = vmState.parcelDeliveryAllowed,
            continueButtonUiState = ButtonUiState(
                text = UiText.Resource(Res.string.create_trip_continue),
                enabled = isValid,
            ),
        )
    }
}
