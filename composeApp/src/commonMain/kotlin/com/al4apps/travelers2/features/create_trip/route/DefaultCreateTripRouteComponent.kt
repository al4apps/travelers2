package com.al4apps.travelers2.features.create_trip.route

import com.al4apps.travelers2.core.CoroutineFeature
import com.al4apps.travelers2.core.CoroutineFeatureImpl
import com.al4apps.travelers2.core.StateStore
import com.al4apps.travelers2.core.model.TripAddress
import com.al4apps.travelers2.core.model.TripDate
import com.al4apps.travelers2.features.create_trip.domain.usecase.ObserveAuthStateUseCase
import com.al4apps.travelers2.features.create_trip.domain.usecase.ObserveRecentTripsUseCase
import com.al4apps.travelers2.features.create_trip.model.CreateTripDraft
import com.al4apps.travelers2.features.create_trip.model.RecentTrip
import com.al4apps.travelers2.features.create_trip.route.map.CreateTripRouteUiAssembler
import com.al4apps.travelers2.features.create_trip.route.model.CreateTripRouteUiState
import com.al4apps.travelers2.features.create_trip.route.model.CreateTripRouteVmState
import com.al4apps.travelers2.features.search_form.address_search.AddressSearchCatalog
import com.al4apps.travelers2.features.search_form.address_search.DefaultAddressSearchComponent
import com.al4apps.travelers2.features.search_form.calendar.DefaultCalendarComponent
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.pushNew
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class DefaultCreateTripRouteComponent(
    componentContext: ComponentContext,
    initialDraft: CreateTripDraft,
    observeRecentTripsUseCase: ObserveRecentTripsUseCase,
    observeAuthStateUseCase: ObserveAuthStateUseCase,
    /** Mirrors the route slice into the parent draft on every mutation. */
    private val onSliceChanged: (
        fromAddress: TripAddress?,
        toAddress: TripAddress?,
        tripDate: TripDate?,
    ) -> Unit,
    private val onCommitRoute: () -> Unit,
    private val uiAssembler: CreateTripRouteUiAssembler = CreateTripRouteUiAssembler(),
) : CreateTripRouteComponent,
    ComponentContext by componentContext,
    CoroutineFeature by CoroutineFeatureImpl(componentContext) {

    private val navigation = StackNavigation<RouteSubConfig>()

    private val vmStateStore = StateStore(
        initialState = stateKeeper.consume(
            key = STATE_KEY,
            strategy = CreateTripRouteVmState.serializer(),
        ) ?: CreateTripRouteVmState(
            fromAddress = initialDraft.fromAddress,
            toAddress = initialDraft.toAddress,
            tripDate = initialDraft.tripDate,
        ),
    )

    private val recentTripsValue: MutableValue<List<RecentTrip>> = MutableValue(emptyList())
    private val isAuthenticatedValue: MutableValue<Boolean> = MutableValue(true)

    private val mutableUiState: MutableValue<CreateTripRouteUiState> = MutableValue(
        uiAssembler.assemble(
            vmState = vmStateStore.value,
            recentTrips = emptyList(),
            isAuthenticated = true,
        ),
    )

    override val uiState: Value<CreateTripRouteUiState> = mutableUiState

    override val stack: Value<ChildStack<RouteSubConfig, RouteSubChild>> = childStack(
        source = navigation,
        serializer = RouteSubConfig.serializer(),
        initialConfiguration = RouteSubConfig.Form,
        handleBackButton = true,
        childFactory = ::createChild,
    )

    init {
        stateKeeper.register(
            key = STATE_KEY,
            strategy = CreateTripRouteVmState.serializer(),
            supplier = vmStateStore::value,
        )
        vmStateStore.stateFlow
            .onEach { reassemble() }
            .launchIn(featureScope)
        observeRecentTripsUseCase()
            .onEach { trips ->
                recentTripsValue.value = trips
                reassemble()
            }
            .launchIn(featureScope)
        observeAuthStateUseCase()
            .onEach { isAuth ->
                isAuthenticatedValue.value = isAuth
                reassemble()
            }
            .launchIn(featureScope)
    }

    override fun onFromClick() {
        navigation.pushNew(RouteSubConfig.AddressSearch(field = RouteAddressField.FROM))
    }

    override fun onToClick() {
        navigation.pushNew(RouteSubConfig.AddressSearch(field = RouteAddressField.TO))
    }

    override fun onDateClick() {
        navigation.pushNew(RouteSubConfig.Calendar)
    }

    override fun onContinueClick() {
        val state = vmStateStore.value
        if (state.fromAddress == null || state.toAddress == null || state.tripDate == null) return
        propagateSlice()
        onCommitRoute()
    }

    override fun onRecentRepeatClick(recentTripId: String) {
        val trip = recentTripsValue.value.firstOrNull { it.id == recentTripId } ?: return
        mutate { current ->
            current.copy(
                fromAddress = trip.fromAddress,
                toAddress = trip.toAddress,
                tripDate = current.tripDate ?: trip.tripDate,
            )
        }
    }

    private fun createChild(
        config: RouteSubConfig,
        componentContext: ComponentContext,
    ): RouteSubChild =
        when (config) {
            RouteSubConfig.Form -> RouteSubChild.Form

            is RouteSubConfig.AddressSearch -> RouteSubChild.AddressSearch(
                component = DefaultAddressSearchComponent(
                    componentContext = componentContext,
                    title = config.field.toUiText(),
                    placeholder = config.field.toUiText(),
                    initialQuery = "",
                    addresses = AddressSearchCatalog.addresses,
                    onBackClick = { navigation.pop() },
                    onAddressClick = { address ->
                        applyAddress(field = config.field, address = address)
                    },
                    stateKey = "create_trip_address_search_${config.field.name}",
                ),
            )

            RouteSubConfig.Calendar -> RouteSubChild.Calendar(
                component = DefaultCalendarComponent(
                    componentContext = componentContext,
                    initialDate = vmStateStore.value.tripDate,
                    onDateSelected = ::applyDate,
                    onCloseClick = { navigation.pop() },
                ),
            )
        }

    private fun applyAddress(field: RouteAddressField, address: TripAddress) {
        mutate {
            when (field) {
                RouteAddressField.FROM -> it.copy(fromAddress = address)
                RouteAddressField.TO -> it.copy(toAddress = address)
            }
        }
        navigation.pop()
    }

    private fun applyDate(date: TripDate) {
        mutate { it.copy(tripDate = date) }
        navigation.pop()
    }

    private fun mutate(block: (CreateTripRouteVmState) -> CreateTripRouteVmState) {
        vmStateStore.updateState(block)
        propagateSlice()
    }

    private fun propagateSlice() {
        val state = vmStateStore.value
        onSliceChanged(state.fromAddress, state.toAddress, state.tripDate)
    }

    private fun reassemble() {
        mutableUiState.value = uiAssembler.assemble(
            vmState = vmStateStore.value,
            recentTrips = recentTripsValue.value,
            isAuthenticated = isAuthenticatedValue.value,
        )
    }

    private companion object {
        const val STATE_KEY = "create_trip_route_vm_state"
    }
}
