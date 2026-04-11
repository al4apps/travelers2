package com.al4apps.travelers2.features.search_trips.address_search

import com.al4apps.travelers2.core.CoroutineFeature
import com.al4apps.travelers2.core.CoroutineFeatureImpl
import com.al4apps.travelers2.core.StateStore
import com.al4apps.travelers2.core.toUiState
import com.al4apps.travelers2.features.search_trips.SearchTripsAddressField
import com.al4apps.travelers2.features.search_trips.address_search.map.AddressSearchUiAssembler
import com.al4apps.travelers2.features.search_trips.address_search.model.AddressSearchUiState
import com.al4apps.travelers2.features.search_trips.address_search.model.AddressSearchVmState
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value

class DefaultAddressSearchComponent(
    componentContext: ComponentContext,
    private val field: SearchTripsAddressField,
    initialQuery: String,
    private val addresses: List<String>,
    private val onBackClick: () -> Unit,
    private val onAddressClick: (String) -> Unit,
    private val uiAssembler: AddressSearchUiAssembler = AddressSearchUiAssembler(),
) : AddressSearchComponent,
    ComponentContext by componentContext,
    CoroutineFeature by CoroutineFeatureImpl(componentContext) {

    private val vmStateStore = StateStore(
        initialState = stateKeeper.consume(
            key = STATE_KEY,
            strategy = AddressSearchVmState.serializer(),
        ) ?: AddressSearchVmState(query = initialQuery),
    )

    override val uiState: Value<AddressSearchUiState> = vmStateStore.toUiState(
        scope = featureScope,
        assemble = {
            uiAssembler.assemble(
                vmState = vmStateStore.value,
                field = field,
                addresses = addresses,
            )
        }
    )

    init {
        stateKeeper.register(
            key = STATE_KEY,
            strategy = AddressSearchVmState.serializer(),
            supplier = vmStateStore::value,
        )
    }

    override fun onQueryChange(value: String) {
        vmStateStore.updateState {
            it.copy(query = value)
        }
    }

    override fun onBackClick() {
        onBackClick.invoke()
    }

    override fun onAddressClick(address: String) {
        onAddressClick.invoke(address)
    }

    private companion object {
        const val STATE_KEY = "address_search_vm_state"
    }
}
