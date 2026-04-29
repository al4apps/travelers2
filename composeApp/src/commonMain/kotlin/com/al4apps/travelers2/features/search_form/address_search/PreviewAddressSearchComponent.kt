package com.al4apps.travelers2.features.search_form.address_search

import com.al4apps.travelers2.core.model.TripAddress
import com.al4apps.travelers2.core.ui.UiText
import com.al4apps.travelers2.features.search_form.address_search.map.AddressSearchUiAssembler
import com.al4apps.travelers2.features.search_form.address_search.model.AddressSearchUiState
import com.al4apps.travelers2.features.search_form.address_search.model.AddressSearchVmState
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import travelers2.composeapp.generated.resources.Res
import travelers2.composeapp.generated.resources.search_trips_from

class PreviewAddressSearchComponent : AddressSearchComponent {
    override val uiState: Value<AddressSearchUiState> =
        MutableValue(
            AddressSearchUiAssembler().assemble(
                vmState = AddressSearchVmState(query = "Душ"),
                title = UiText.Resource(Res.string.search_trips_from),
                placeholder = UiText.Resource(Res.string.search_trips_from),
                addresses = AddressSearchCatalog.addresses,
            ),
        )

    override fun onQueryChange(value: String) = Unit

    override fun onBackClick() = Unit

    override fun onAddressClick(address: TripAddress) = Unit
}
