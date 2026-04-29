package com.al4apps.travelers2.features.home

import com.al4apps.travelers2.core.CoroutineFeature
import com.al4apps.travelers2.core.CoroutineFeatureImpl
import com.al4apps.travelers2.core.StateStore
import com.al4apps.travelers2.core.toUiState
import com.al4apps.travelers2.di.CreateTripDiContainer
import com.al4apps.travelers2.features.create_trip.DefaultCreateTripComponent
import com.al4apps.travelers2.features.home.domain.usecase.InitializeHomeUseCase
import com.al4apps.travelers2.features.home.map.HomeUiAssembler
import com.al4apps.travelers2.features.home.model.HomeUiState
import com.al4apps.travelers2.features.home.model.HomeVmState
import com.al4apps.travelers2.features.profile.DefaultProfileComponent
import com.al4apps.travelers2.features.search.DefaultSearchFeatureComponent
import com.al4apps.travelers2.features.trip_details.domain.usecase.HandleDriverContactActionUseCase
import com.al4apps.travelers2.shared_model.DockItem
import com.al4apps.travelers2.shared_model.DockItemUiState
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import kotlinx.coroutines.launch

class DefaultHomeComponent(
    componentContext: ComponentContext,
    private val initializeHomeUseCase: InitializeHomeUseCase,
    private val handleDriverContactActionUseCase: HandleDriverContactActionUseCase,
    private val createTripDiContainerFactory: () -> CreateTripDiContainer,
    private val uiAssembler: HomeUiAssembler = HomeUiAssembler(),
) : HomeComponent,
    ComponentContext by componentContext,
    CoroutineFeature by CoroutineFeatureImpl(componentContext) {

    private val navigation = StackNavigation<HomeConfig>()

    private val vmStateStore = StateStore(
        initialState = stateKeeper.consume(
            key = STATE_KEY,
            strategy = HomeVmState.serializer(),
        ) ?: HomeVmState(),
    )

    override val stack: Value<ChildStack<HomeConfig, HomeChild>> = childStack(
        source = navigation,
        serializer = HomeConfig.serializer(),
        initialConfiguration = HomeConfig.SearchTrips,
        handleBackButton = true,
        childFactory = ::createChild,
    )

    override val uiState: Value<HomeUiState> =
        vmStateStore.toUiState(
            scope = featureScope,
            assemble = uiAssembler::assemble,
        )

    init {
        stateKeeper.register(
            key = STATE_KEY,
            strategy = HomeVmState.serializer(),
            supplier = vmStateStore::value,
        )
        bootstrap()
    }

    override fun onRetryClick() {
        bootstrap()
    }

    override fun onDockItemClick(item: DockItem) {
        navigation.pushToFront(item.toConfig())
    }

    override fun getDockItems(): List<DockItemUiState> {
        val activeItem = stack.value.active.configuration.toDockItem()

        return DockItem.entries.map { item ->
            DockItemUiState(
                item = item,
                isSelected = item == activeItem,
            )
        }
    }

    private fun bootstrap() {
        vmStateStore.updateState {
            it.copy(
                isLoading = true,
                errorMessage = null,
            )
        }

        featureScope.launch {
            runCatching {
                initializeHomeUseCase()
            }.onSuccess {
                vmStateStore.updateState {
                    it.copy(
                        isLoading = false,
                        errorMessage = null,
                    )
                }
            }.onFailure { throwable ->
                vmStateStore.updateState {
                    it.copy(
                        isLoading = false,
                        errorMessage = throwable.message ?: "Home initialization failed.",
                    )
                }
            }
        }
    }

    private fun createChild(
        config: HomeConfig,
        componentContext: ComponentContext,
    ): HomeChild =
        when (config) {
            HomeConfig.SearchTrips -> {
                val component = DefaultSearchFeatureComponent(
                    componentContext = componentContext,
                    updateDockVisibility = ::updateDockVisibility,
                    handleDriverContactActionUseCase = handleDriverContactActionUseCase,
                )
                HomeChild.SearchTrips(
                    component = component,
                )
            }

            HomeConfig.CreateTrip -> {
                val createTripDiContainer = createTripDiContainerFactory()
                HomeChild.CreateTrip(
                    component = DefaultCreateTripComponent(
                        componentContext = componentContext,
                        observeRecentTripsUseCase = createTripDiContainer.observeRecentTripsUseCase,
                        getUserVehiclesUseCase = createTripDiContainer.getUserVehiclesUseCase,
                        publishTripUseCase = createTripDiContainer.publishTripUseCase,
                        observeAuthStateUseCase = createTripDiContainer.observeAuthStateUseCase,
                        updateDockVisibility = ::updateDockVisibility,
                    ),
                )
            }

            HomeConfig.Profile -> {
                HomeChild.Profile(
                    component = DefaultProfileComponent(componentContext = componentContext),
                )
            }
        }

    private fun updateDockVisibility(isVisible: Boolean) {
        vmStateStore.updateState { state ->
            state.copy(isDockVisible = isVisible)
        }
    }

    private companion object {
        const val STATE_KEY = "home_vm_state"
    }
}

private fun StackNavigation<HomeConfig>.pushToFront(config: HomeConfig) {
    bringToFront(config)
}

private fun DockItem.toConfig(): HomeConfig =
    when (this) {
        DockItem.SEARCH_TRIPS -> HomeConfig.SearchTrips
        DockItem.CREATE_TRIP -> HomeConfig.CreateTrip
        DockItem.PROFILE -> HomeConfig.Profile
    }

private fun HomeConfig.toDockItem(): DockItem =
    when (this) {
        HomeConfig.SearchTrips -> DockItem.SEARCH_TRIPS
        HomeConfig.CreateTrip -> DockItem.CREATE_TRIP
        HomeConfig.Profile -> DockItem.PROFILE
    }
