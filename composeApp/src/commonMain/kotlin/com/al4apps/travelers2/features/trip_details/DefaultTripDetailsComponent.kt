package com.al4apps.travelers2.features.trip_details

import com.al4apps.travelers2.core.CoroutineFeature
import com.al4apps.travelers2.core.CoroutineFeatureImpl
import com.al4apps.travelers2.core.StateStore
import com.al4apps.travelers2.core.toUiState
import com.al4apps.travelers2.features.trip_details.map.TripDetailsUiAssembler
import com.al4apps.travelers2.features.trip_details.model.TripCarVmModel
import com.al4apps.travelers2.features.trip_details.model.TripDriverVmModel
import com.al4apps.travelers2.features.trip_details.model.TripPointVmModel
import com.al4apps.travelers2.features.trip_details.model.TripDetailsUiState
import com.al4apps.travelers2.features.trip_details.model.TripDetailsVmState
import com.al4apps.travelers2.features.trip_details.model.TripSeatType
import com.al4apps.travelers2.features.trip_details.model.TripSeatVmModel
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value

class DefaultTripDetailsComponent(
    componentContext: ComponentContext,
    tripId: String,
    private val onBack: () -> Unit,
    private val onBook: () -> Unit = {},
    private val uiAssembler: TripDetailsUiAssembler = TripDetailsUiAssembler(),
) : TripDetailsComponent,
    ComponentContext by componentContext,
    CoroutineFeature by CoroutineFeatureImpl(componentContext) {

    private val vmStateStore = StateStore(
        initialState = stateKeeper.consume(
            key = STATE_KEY,
            strategy = TripDetailsVmState.serializer(),
        ) ?: sampleVmState(tripId = tripId),
    )

    override val uiState: Value<TripDetailsUiState> =
        vmStateStore.toUiState(
            scope = featureScope,
            assemble = uiAssembler::assemble,
        )

    init {
        stateKeeper.register(
            key = STATE_KEY,
            strategy = TripDetailsVmState.serializer(),
            supplier = vmStateStore::value,
        )
    }

    override fun onBackClick() {
        onBack.invoke()
    }

    override fun onBookClick() {
        onBook.invoke()
    }

    private companion object {
        const val STATE_KEY = "trip_details_vm_state"
    }
}

private fun sampleVmState(tripId: String): TripDetailsVmState =
    TripDetailsVmState(
        tripId = tripId,
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
    )
