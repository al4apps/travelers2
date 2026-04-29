package com.al4apps.travelers2.features.create_trip

import com.al4apps.travelers2.core.CoroutineFeature
import com.al4apps.travelers2.core.CoroutineFeatureImpl
import com.al4apps.travelers2.core.StateStore
import com.al4apps.travelers2.features.create_trip.confirm.DefaultCreateTripConfirmComponent
import com.al4apps.travelers2.features.create_trip.domain.usecase.GetUserVehiclesUseCase
import com.al4apps.travelers2.features.create_trip.domain.usecase.ObserveAuthStateUseCase
import com.al4apps.travelers2.features.create_trip.domain.usecase.ObserveRecentTripsUseCase
import com.al4apps.travelers2.features.create_trip.domain.usecase.PublishTripUseCase
import com.al4apps.travelers2.features.create_trip.model.CreateTripDraft
import com.al4apps.travelers2.features.create_trip.route.DefaultCreateTripRouteComponent
import com.al4apps.travelers2.features.create_trip.time.DefaultCreateTripTimeComponent
import com.al4apps.travelers2.features.create_trip.vehicle.DefaultCreateTripVehicleComponent
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.router.slot.SlotNavigation
import com.arkivanov.decompose.router.slot.activate
import com.arkivanov.decompose.router.slot.childSlot
import com.arkivanov.decompose.router.slot.dismiss
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.pushNew
import com.arkivanov.decompose.router.stack.replaceAll
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.lifecycle.doOnDestroy

class DefaultCreateTripComponent(
    componentContext: ComponentContext,
    private val observeRecentTripsUseCase: ObserveRecentTripsUseCase,
    private val getUserVehiclesUseCase: GetUserVehiclesUseCase,
    private val publishTripUseCase: PublishTripUseCase,
    private val observeAuthStateUseCase: ObserveAuthStateUseCase,
    private val updateDockVisibility: (isVisible: Boolean) -> Unit,
) : CreateTripComponent,
    ComponentContext by componentContext,
    CoroutineFeature by CoroutineFeatureImpl(componentContext) {

    private val navigation = StackNavigation<CreateTripConfig>()
    private val dialogNavigation = SlotNavigation<CreateTripDialogConfig>()

    private val draftStore = StateStore(
        initialState = stateKeeper.consume(
            key = DRAFT_KEY,
            strategy = CreateTripDraft.serializer(),
        ) ?: CreateTripDraft(),
    )

    override val stack: Value<ChildStack<CreateTripConfig, CreateTripChild>> = childStack(
        source = navigation,
        serializer = CreateTripConfig.serializer(),
        initialConfiguration = CreateTripConfig.Route,
        handleBackButton = true,
        childFactory = ::createChild,
    )

    override val dialogSlot: Value<ChildSlot<CreateTripDialogConfig, CreateTripDialogChild>> = childSlot(
        source = dialogNavigation,
        serializer = CreateTripDialogConfig.serializer(),
        key = DIALOG_KEY,
        handleBackButton = true,
        childFactory = ::createDialogChild,
    )

    init {
        stateKeeper.register(
            key = DRAFT_KEY,
            strategy = CreateTripDraft.serializer(),
            supplier = draftStore::value,
        )
        observeStackForDock()
    }

    override fun onPublishedAcknowledged() {
        dialogNavigation.dismiss()
        draftStore.updateState { CreateTripDraft() }
        navigation.replaceAll(CreateTripConfig.Route)
    }

    private fun observeStackForDock() {
        val cancellation = stack.subscribe { childStack ->
            val isOnRoot = childStack.active.configuration is CreateTripConfig.Route
            updateDockVisibility(isOnRoot)
        }
        doOnDestroy {
            cancellation.cancel()
            updateDockVisibility(true)
        }
    }

    private fun createChild(
        config: CreateTripConfig,
        componentContext: ComponentContext,
    ): CreateTripChild =
        when (config) {
            CreateTripConfig.Route -> CreateTripChild.Route(
                component = DefaultCreateTripRouteComponent(
                    componentContext = componentContext,
                    initialDraft = draftStore.value,
                    observeRecentTripsUseCase = observeRecentTripsUseCase,
                    observeAuthStateUseCase = observeAuthStateUseCase,
                    onSliceChanged = { from, to, date ->
                        draftStore.updateState {
                            it.copy(fromAddress = from, toAddress = to, tripDate = date)
                        }
                    },
                    onCommitRoute = { navigation.pushNew(CreateTripConfig.Time) },
                ),
            )

            CreateTripConfig.Time -> CreateTripChild.Time(
                component = DefaultCreateTripTimeComponent(
                    componentContext = componentContext,
                    initialDraft = draftStore.value,
                    onSliceChanged = { departure, duration, meetingPoint ->
                        draftStore.updateState {
                            it.copy(
                                departureTime = departure,
                                duration = duration,
                                meetingPoint = meetingPoint,
                            )
                        }
                    },
                    onCommitTime = { navigation.pushNew(CreateTripConfig.Vehicle) },
                    onBack = { navigation.pop() },
                ),
            )

            CreateTripConfig.Vehicle -> CreateTripChild.Vehicle(
                component = DefaultCreateTripVehicleComponent(
                    componentContext = componentContext,
                    initialDraft = draftStore.value,
                    getUserVehiclesUseCase = getUserVehiclesUseCase,
                    onSliceChanged = { vehicleId, seats, price, baggage, parcel ->
                        draftStore.updateState {
                            it.copy(
                                selectedVehicleId = vehicleId,
                                freeSeatsCount = seats,
                                pricePerSeat = price,
                                baggageAllowed = baggage,
                                parcelDeliveryAllowed = parcel,
                            )
                        }
                    },
                    onCommitVehicle = { navigation.pushNew(CreateTripConfig.Confirm) },
                    onBack = { navigation.pop() },
                ),
            )

            CreateTripConfig.Confirm -> CreateTripChild.Confirm(
                component = DefaultCreateTripConfirmComponent(
                    componentContext = componentContext,
                    draft = draftStore.value,
                    publishTripUseCase = publishTripUseCase,
                    getUserVehiclesUseCase = getUserVehiclesUseCase,
                    onBack = { navigation.pop() },
                    onPublished = { dialogNavigation.activate(CreateTripDialogConfig.Published) },
                ),
            )
        }

    private fun createDialogChild(
        config: CreateTripDialogConfig,
        @Suppress("UNUSED_PARAMETER") componentContext: ComponentContext,
    ): CreateTripDialogChild =
        when (config) {
            CreateTripDialogConfig.Published -> CreateTripDialogChild.Published
        }

    private companion object {
        const val DRAFT_KEY = "create_trip_draft"
        const val DIALOG_KEY = "create_trip_dialog_slot"
    }
}
