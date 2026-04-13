package com.al4apps.travelers2.features.search_trips

import com.al4apps.travelers2.core.CoroutineFeature
import com.al4apps.travelers2.core.CoroutineFeatureImpl
import com.al4apps.travelers2.core.StateStore
import com.al4apps.travelers2.core.log.KmpLogger
import com.al4apps.travelers2.core.log.kmpLogger
import com.al4apps.travelers2.core.model.TripAddress
import com.al4apps.travelers2.core.model.TripDate
import com.al4apps.travelers2.core.model.toDisplayString
import com.al4apps.travelers2.core.toUiState
import com.al4apps.travelers2.features.search_trips.address_search.AddressSearchCatalog
import com.al4apps.travelers2.features.search_trips.address_search.DefaultAddressSearchComponent
import com.al4apps.travelers2.features.search_trips.calendar.DefaultCalendarComponent
import com.al4apps.travelers2.features.search_trips.map.SearchTripsUiAssembler
import com.al4apps.travelers2.features.search_trips.model.SearchTripsUiState
import com.al4apps.travelers2.features.search_trips.model.SearchTripsVmState
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.pushNew
import com.arkivanov.decompose.value.Value

class DefaultSearchFormComponent(
    componentContext: ComponentContext,
    private val onSearchRequested: (fromAddress: TripAddress?, toAddress: TripAddress?, tripDate: TripDate?) -> Unit = { _, _, _ -> },
    private val uiAssembler: SearchTripsUiAssembler = SearchTripsUiAssembler(),
    private val logger: KmpLogger = kmpLogger<DefaultSearchFormComponent>()
) : SearchFormComponent,
    ComponentContext by componentContext,
    CoroutineFeature by CoroutineFeatureImpl(componentContext) {

    private val navigation = StackNavigation<SearchTripsConfig>()

    private val vmStateStore = StateStore(
        initialState = stateKeeper.consume(
            key = STATE_KEY,
            strategy = SearchTripsVmState.serializer(),
        ) ?: SearchTripsVmState(),
    )

    override val uiState: Value<SearchTripsUiState> =
        vmStateStore.toUiState(
            scope = featureScope,
            assemble = uiAssembler::assemble,
        )

    override val stack: Value<ChildStack<SearchTripsConfig, SearchTripsChild>> = childStack(
        source = navigation,
        serializer = SearchTripsConfig.serializer(),
        initialConfiguration = SearchTripsConfig.Form,
        handleBackButton = true,
        childFactory = ::createChild,
    )

    private fun createChild(
        config: SearchTripsConfig,
        componentContext: ComponentContext,
    ): SearchTripsChild =
        when (config) {
            SearchTripsConfig.Form -> SearchTripsChild.Form

            SearchTripsConfig.Calendar -> SearchTripsChild.Calendar(
                component = DefaultCalendarComponent(
                    componentContext = componentContext,
                    initialDate = vmStateStore.value.tripDate,
                    onDateSelected = ::onDateSelected,
                    onCloseClick = ::closeCalendar,
                ),
            )

            is SearchTripsConfig.AddressSearch -> SearchTripsChild.AddressSearch(
                component = DefaultAddressSearchComponent(
                    componentContext = componentContext,
                    field = config.field,
                    initialQuery = config.field.extractValue(vmStateStore.value),
                    addresses = AddressSearchCatalog.addresses,
                    onBackClick = ::closeAddressSearch,
                    onAddressClick = { address ->
                        applySelectedAddress(
                            field = config.field,
                            address = address,
                        )
                    },
                ),
            )
        }

    init {
        registerStateKeeper()
    }

    override fun onFromClick() {
        logger.debug("onFromClick")
        navigation.pushNew(SearchTripsConfig.AddressSearch(SearchTripsAddressField.FROM))
    }

    override fun onToClick() {
        logger.debug("onToClick")
        navigation.pushNew(SearchTripsConfig.AddressSearch(SearchTripsAddressField.TO))
    }

    override fun onDateClick() {
        logger.debug("onDateClick")
        navigation.pushNew(SearchTripsConfig.Calendar)
    }

    override fun onSearchClick() {
        val vmState = vmStateStore.value
        onSearchRequested(
            vmState.fromAddress,
            vmState.toAddress,
            vmState.tripDate,
        )
    }

    private fun closeAddressSearch() {
        navigation.pop()
    }

    private fun applySelectedAddress(
        field: SearchTripsAddressField,
        address: TripAddress,
    ) {
        vmStateStore.updateState { state ->
            when (field) {
                SearchTripsAddressField.FROM -> state.copy(fromAddress = address)
                SearchTripsAddressField.TO -> state.copy(toAddress = address)
            }
        }

        closeAddressSearch()
    }

    private fun onDateSelected(value: TripDate) {
        vmStateStore.updateState {
            it.copy(tripDate = value)
        }
        navigation.pop()
    }

    private fun closeCalendar() {
        navigation.pop()
    }

    private fun registerStateKeeper() {
        stateKeeper.register(
            key = STATE_KEY,
            strategy = SearchTripsVmState.serializer(),
            supplier = vmStateStore::value,
        )
    }

    private companion object {
        const val STATE_KEY = "search_trips_vm_state"
    }
}

private fun SearchTripsAddressField.extractValue(state: SearchTripsVmState): String =
    when (this) {
        SearchTripsAddressField.FROM -> state.fromAddress?.toDisplayString().orEmpty()
        SearchTripsAddressField.TO -> state.toAddress?.toDisplayString().orEmpty()
    }
