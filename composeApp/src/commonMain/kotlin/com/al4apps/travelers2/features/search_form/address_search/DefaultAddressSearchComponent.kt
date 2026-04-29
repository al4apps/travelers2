package com.al4apps.travelers2.features.search_form.address_search

import com.al4apps.travelers2.core.CoroutineFeature
import com.al4apps.travelers2.core.CoroutineFeatureImpl
import com.al4apps.travelers2.core.StateStore
import com.al4apps.travelers2.core.toUiState
import com.al4apps.travelers2.core.model.TripAddress
import com.al4apps.travelers2.core.ui.UiText
import com.al4apps.travelers2.features.search_form.address_search.map.AddressSearchUiAssembler
import com.al4apps.travelers2.features.search_form.address_search.model.AddressSearchUiState
import com.al4apps.travelers2.features.search_form.address_search.model.AddressSearchVmState
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value

class DefaultAddressSearchComponent(
    componentContext: ComponentContext,
    private val title: UiText,
    private val placeholder: UiText,
    initialQuery: String,
    private val addresses: List<TripAddress>,
    private val onBackClick: () -> Unit,
    private val onAddressClick: (TripAddress) -> Unit,
    private val uiAssembler: AddressSearchUiAssembler = AddressSearchUiAssembler(),
    private val stateKey: String = DEFAULT_STATE_KEY,
) : AddressSearchComponent,
    ComponentContext by componentContext,
    CoroutineFeature by CoroutineFeatureImpl(componentContext) {

    private val vmStateStore = StateStore(
        initialState = stateKeeper.consume(
            key = stateKey,
            strategy = AddressSearchVmState.serializer(),
        ) ?: AddressSearchVmState(query = initialQuery),
    )

    override val uiState: Value<AddressSearchUiState> = vmStateStore.toUiState(
        scope = featureScope,
        assemble = { vmState ->
            uiAssembler.assemble(
                vmState = vmState,
                title = title,
                placeholder = placeholder,
                addresses = addresses,
            )
        },
    )

    init {
        stateKeeper.register(
            key = stateKey,
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

    override fun onAddressClick(address: TripAddress) {
        onAddressClick.invoke(address)
    }

    private companion object {
        const val DEFAULT_STATE_KEY = "address_search_vm_state"
    }
}
