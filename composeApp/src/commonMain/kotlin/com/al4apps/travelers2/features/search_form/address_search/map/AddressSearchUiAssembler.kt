package com.al4apps.travelers2.features.search_form.address_search.map

import com.al4apps.travelers2.core.model.TripAddress
import com.al4apps.travelers2.core.model.matchesQuery
import com.al4apps.travelers2.core.ui.UiText
import com.al4apps.travelers2.features.search_form.address_search.model.AddressSearchUiState
import com.al4apps.travelers2.features.search_form.address_search.model.AddressSearchVmState

class AddressSearchUiAssembler {

    fun assemble(
        vmState: AddressSearchVmState,
        title: UiText,
        placeholder: UiText,
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
            title = title,
            placeholder = placeholder,
            query = vmState.query,
            results = filteredAddresses,
        )
    }
}
