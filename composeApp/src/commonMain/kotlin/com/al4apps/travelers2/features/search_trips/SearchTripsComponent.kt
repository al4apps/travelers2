package com.al4apps.travelers2.features.search_trips

import com.al4apps.travelers2.features.search_trips.model.SearchTripsUiState
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value

interface SearchTripsComponent {
    val uiState: Value<SearchTripsUiState>
    val stack: Value<ChildStack<SearchTripsConfig, SearchTripsChild>>

    fun onFromClick()

    fun onToClick()

    fun onDateClick()

    fun onSearchClick()
}
