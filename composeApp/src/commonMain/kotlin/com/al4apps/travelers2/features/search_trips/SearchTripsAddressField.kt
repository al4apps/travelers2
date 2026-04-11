package com.al4apps.travelers2.features.search_trips

import com.al4apps.travelers2.core.ui.UiText
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.StringResource
import travelers2.composeapp.generated.resources.Res
import travelers2.composeapp.generated.resources.search_trips_from
import travelers2.composeapp.generated.resources.search_trips_to

@Serializable
enum class SearchTripsAddressField(
    val titleRes: StringResource,
) {
    FROM(titleRes = Res.string.search_trips_from),
    TO(titleRes = Res.string.search_trips_to),
}

fun SearchTripsAddressField.toUiText(): UiText = UiText.Resource(titleRes)
