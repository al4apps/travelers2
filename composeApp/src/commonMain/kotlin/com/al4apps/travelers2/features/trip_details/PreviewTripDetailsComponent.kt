package com.al4apps.travelers2.features.trip_details

import com.al4apps.travelers2.features.trip_details.map.TripDetailsUiAssembler
import com.al4apps.travelers2.features.trip_details.model.TripCarVmModel
import com.al4apps.travelers2.features.trip_details.model.TripDriverVmModel
import com.al4apps.travelers2.features.trip_details.model.TripPointVmModel
import com.al4apps.travelers2.features.trip_details.model.TripDetailsUiState
import com.al4apps.travelers2.features.trip_details.model.TripDetailsVmState
import com.al4apps.travelers2.features.trip_details.model.TripSeatType
import com.al4apps.travelers2.features.trip_details.model.TripSeatVmModel
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value

class PreviewTripDetailsComponent : TripDetailsComponent {
    override val uiState: Value<TripDetailsUiState> =
        MutableValue(
            TripDetailsUiAssembler().assemble(
                vmState = TripDetailsVmState(
                    tripId = "preview_trip",
                    tripDateText = "20.04.2026",
                    driver = TripDriverVmModel(
                        name = "Искандар",
                        completedTripsCount = 150,
                    ),
                    departurePoint = TripPointVmModel(
                        time = "09:00",
                        city = "Душанбе",
                        district = "Фирдавси",
                    ),
                    arrivalPoint = TripPointVmModel(
                        time = "12:00",
                        city = "Худжанд",
                        district = "Панчшанбе",
                    ),
                    car = TripCarVmModel(
                        model = "Toyota Camry",
                        plateNumber = "0001 TJ 01",
                    ),
                    baggageAllowed = true,
                    parcelDeliveryAllowed = true,
                    freeSeatsCount = 2,
                    seats = listOf(
                        TripSeatVmModel(id = "seat_driver", type = TripSeatType.DRIVER),
                        TripSeatVmModel(id = "seat_1", type = TripSeatType.OCCUPIED),
                        TripSeatVmModel(id = "seat_2", type = TripSeatType.OCCUPIED),
                        TripSeatVmModel(id = "seat_3", type = TripSeatType.AVAILABLE),
                        TripSeatVmModel(id = "seat_4", type = TripSeatType.AVAILABLE),
                    ),
                    priceText = "200 с.",
                ),
            ),
        )

    override fun onBackClick() = Unit

    override fun onBookClick() = Unit
}
