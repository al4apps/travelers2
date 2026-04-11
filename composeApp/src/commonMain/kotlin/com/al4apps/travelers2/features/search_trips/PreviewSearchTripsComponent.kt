package com.al4apps.travelers2.features.search_trips

import com.al4apps.travelers2.features.search_trips.map.SearchTripsUiAssembler
import com.al4apps.travelers2.features.search_trips.model.SearchTripsUiState
import com.al4apps.travelers2.features.search_trips.model.SearchTripsVmState
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value

class PreviewSearchTripsComponent : SearchTripsComponent {
    override val uiState: Value<SearchTripsUiState> =
        MutableValue(
            SearchTripsUiAssembler().assemble(
                SearchTripsVmState(
                    fromAddress = "",
                    toAddress = "",
                    tripDate = "",
                ),
            ),
        )

    override val stack: Value<ChildStack<SearchTripsConfig, SearchTripsChild>> =
        MutableValue(
            ChildStack(
                configuration = SearchTripsConfig.Form,
                instance = SearchTripsChild.Form,
            ),
        )

    override fun onFromClick() = Unit

    override fun onToClick() = Unit

    override fun onDateChange(value: String) = Unit

    override fun onSearchClick() = Unit
}
