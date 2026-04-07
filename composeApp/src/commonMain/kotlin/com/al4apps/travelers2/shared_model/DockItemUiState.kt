package com.al4apps.travelers2.shared_model

import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import travelers2.composeapp.generated.resources.Res
import travelers2.composeapp.generated.resources.dock_create_trip
import travelers2.composeapp.generated.resources.dock_profile
import travelers2.composeapp.generated.resources.dock_search_trips
import travelers2.composeapp.generated.resources.plus_circle
import travelers2.composeapp.generated.resources.profile
import travelers2.composeapp.generated.resources.search

data class DockItemUiState(
    val item: DockItem,
    val isSelected: Boolean = false,
    val isEnabled: Boolean = true
)

enum class DockItem(
    val titleRes: StringResource,
    val iconRes: DrawableResource
) {
    SEARCH_TRIPS(
        titleRes = Res.string.dock_search_trips,
        iconRes = Res.drawable.search
    ),
    CREATE_TRIP(
        titleRes = Res.string.dock_create_trip,
        iconRes = Res.drawable.plus_circle
    ),
    PROFILE(
        titleRes = Res.string.dock_profile,
        iconRes = Res.drawable.profile
    )
}
