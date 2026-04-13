package com.al4apps.travelers2.features.search_trips.address_search

import com.al4apps.travelers2.core.model.TripAddress
import com.al4apps.travelers2.features.search_trips.address_search.model.AddressSearchUiState
import com.arkivanov.decompose.value.Value

interface AddressSearchComponent {
    val uiState: Value<AddressSearchUiState>

    fun onQueryChange(value: String)

    fun onBackClick()

    fun onAddressClick(address: TripAddress)
}
