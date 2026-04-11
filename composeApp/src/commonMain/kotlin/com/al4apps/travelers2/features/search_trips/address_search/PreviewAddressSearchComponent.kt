package com.al4apps.travelers2.features.search_trips.address_search

import com.al4apps.travelers2.features.search_trips.SearchTripsAddressField
import com.al4apps.travelers2.features.search_trips.address_search.map.AddressSearchUiAssembler
import com.al4apps.travelers2.features.search_trips.address_search.model.AddressSearchUiState
import com.al4apps.travelers2.features.search_trips.address_search.model.AddressSearchVmState
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value

class PreviewAddressSearchComponent : AddressSearchComponent {
    override val uiState: Value<AddressSearchUiState> =
        MutableValue(
            AddressSearchUiAssembler().assemble(
                vmState = AddressSearchVmState(query = "Душ"),
                field = SearchTripsAddressField.FROM,
                addresses = AddressSearchCatalog.addresses,
            ),
        )

    override fun onQueryChange(value: String) = Unit

    override fun onBackClick() = Unit

    override fun onAddressClick(address: String) = Unit
}
