package com.al4apps.travelers2.features.search_trips.address_search.model

import com.al4apps.travelers2.core.ui.UiText

data class AddressSearchUiState(
    val title: UiText,
    val placeholder: UiText,
    val query: String,
    val results: List<String>,
)
