package com.al4apps.travelers2.features.trip_details

import com.al4apps.travelers2.core.CoroutineFeature
import com.al4apps.travelers2.core.CoroutineFeatureImpl
import com.al4apps.travelers2.core.StateStore
import com.al4apps.travelers2.core.toUiState
import com.al4apps.travelers2.features.trip_details.contact_dialog.DefaultDriverContactDialogComponent
import com.al4apps.travelers2.features.trip_details.contact_dialog.DriverContactAction
import com.al4apps.travelers2.features.trip_details.domain.usecase.HandleDriverContactActionUseCase
import com.al4apps.travelers2.features.trip_details.map.TripDetailsUiAssembler
import com.al4apps.travelers2.features.trip_details.model.TripCarVmModel
import com.al4apps.travelers2.features.trip_details.model.TripDriverVmModel
import com.al4apps.travelers2.features.trip_details.model.TripDriverContact
import com.al4apps.travelers2.features.trip_details.model.TripPointVmModel
import com.al4apps.travelers2.features.trip_details.model.TripDetailsUiState
import com.al4apps.travelers2.features.trip_details.model.TripDetailsVmState
import com.al4apps.travelers2.features.trip_details.model.TripSeatType
import com.al4apps.travelers2.features.trip_details.model.TripSeatVmModel
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.router.slot.SlotNavigation
import com.arkivanov.decompose.router.slot.activate
import com.arkivanov.decompose.router.slot.childSlot
import com.arkivanov.decompose.router.slot.dismiss
import com.arkivanov.decompose.value.Value

class DefaultTripDetailsComponent(
    componentContext: ComponentContext,
    tripId: String,
    private val onBack: () -> Unit,
    private val handleDriverContactActionUseCase: HandleDriverContactActionUseCase,
    private val uiAssembler: TripDetailsUiAssembler = TripDetailsUiAssembler(),
) : TripDetailsComponent,
    ComponentContext by componentContext,
    CoroutineFeature by CoroutineFeatureImpl(componentContext) {

    private val dialogNavigation = SlotNavigation<TripDetailsDialogConfig>()

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

    override val dialogSlot: Value<ChildSlot<TripDetailsDialogConfig, TripDetailsDialogChild>> = childSlot(
        source = dialogNavigation,
        serializer = TripDetailsDialogConfig.serializer(),
        key = DIALOG_SLOT_KEY,
        handleBackButton = true,
        childFactory = ::createDialogChild,
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
        dialogNavigation.activate(TripDetailsDialogConfig.DriverContact)
    }

    private fun createDialogChild(
        config: TripDetailsDialogConfig,
        componentContext: ComponentContext,
    ): TripDetailsDialogChild =
        when (config) {
            TripDetailsDialogConfig.DriverContact -> TripDetailsDialogChild.DriverContact(
                component = DefaultDriverContactDialogComponent(
                    componentContext = componentContext,
                    driverName = vmStateStore.value.driver.name,
                    contact = vmStateStore.value.driverContact,
                    onActionSelected = ::onDriverContactActionSelected,
                    onDismiss = ::dismissDriverContactDialog,
                ),
            )
        }

    private fun onDriverContactActionSelected(action: DriverContactAction) {
        handleDriverContactActionUseCase(
            action = action,
            contact = vmStateStore.value.driverContact,
        )
        dismissDriverContactDialog()
    }

    private fun dismissDriverContactDialog() {
        dialogNavigation.dismiss()
    }

    private companion object {
        const val STATE_KEY = "trip_details_vm_state"
        const val DIALOG_SLOT_KEY = "trip_details_dialog_slot"
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
        driverContact = TripDriverContact(
            phoneNumber = "+992900001122",
            whatsappPhoneNumber = "+992983001001",
            telegramUsername = "@al4gms",
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
