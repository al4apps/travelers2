package com.al4apps.travelers2.features.home

import com.al4apps.travelers2.features.home.model.HomeUiState
import com.al4apps.travelers2.features.search_trips.PreviewSearchTripsComponent
import com.al4apps.travelers2.shared_model.DockItem
import com.al4apps.travelers2.shared_model.DockItemUiState
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value

class PreviewHomeComponent : HomeComponent {
    override val uiState: Value<HomeUiState> =
        MutableValue(
            HomeUiState(
                isLoading = false,
            ),
        )

    override val stack: Value<ChildStack<HomeConfig, HomeChild>> =
        MutableValue(
            ChildStack(
                configuration = HomeConfig.SearchTrips,
                instance = HomeChild.SearchTrips(
                    component = PreviewSearchTripsComponent(),
                ),
            ),
        )

    override fun onRetryClick() = Unit

    override fun onDockItemClick(item: DockItem) = Unit

    override fun getDockItems(): List<DockItemUiState> =
        DockItem.entries.map { item ->
            DockItemUiState(
                item = item,
                isSelected = item == DockItem.SEARCH_TRIPS,
            )
        }
}
