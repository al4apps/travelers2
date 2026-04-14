package com.al4apps.travelers2.features.search_form

import com.al4apps.travelers2.features.search_form.map.SearchTripsUiAssembler
import com.al4apps.travelers2.features.search_form.model.SearchTripsUiState
import com.al4apps.travelers2.features.search_form.model.SearchTripsVmState
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value

class PreviewSearchFormComponent : SearchFormComponent {
    override val uiState: Value<SearchTripsUiState> =
        MutableValue(
            SearchTripsUiAssembler().assemble(
                SearchTripsVmState(),
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

    override fun onDateClick() = Unit

    override fun onSearchClick() = Unit
}
