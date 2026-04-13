package com.al4apps.travelers2.features.search_trips.address_search.map

import com.al4apps.travelers2.core.model.TripAddress
import com.al4apps.travelers2.core.model.matchesQuery
import com.al4apps.travelers2.features.search_trips.SearchTripsAddressField
import com.al4apps.travelers2.features.search_trips.toUiText
import com.al4apps.travelers2.features.search_trips.address_search.model.AddressSearchUiState
import com.al4apps.travelers2.features.search_trips.address_search.model.AddressSearchVmState

class AddressSearchUiAssembler {

    fun assemble(
        vmState: AddressSearchVmState,
        field: SearchTripsAddressField,
        addresses: List<TripAddress>,
    ): AddressSearchUiState {
        val query = vmState.query.trim()
        val filteredAddresses = if (query.isEmpty()) {
            addresses
        } else {
            addresses.filter { address ->
                address.matchesQuery(query)
            }
        }

        return AddressSearchUiState(
            title = field.toUiText(),
            placeholder = field.toUiText(),
            query = vmState.query,
            results = filteredAddresses,
        )
    }
}
