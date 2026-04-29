package com.al4apps.travelers2.features.create_trip.route

import com.al4apps.travelers2.core.ui.UiText
import kotlinx.serialization.Serializable
import travelers2.composeapp.generated.resources.Res
import travelers2.composeapp.generated.resources.search_trips_from
import travelers2.composeapp.generated.resources.search_trips_to

@Serializable
enum class RouteAddressField {
    FROM, TO,
}

internal fun RouteAddressField.toUiText(): UiText = when (this) {
    RouteAddressField.FROM -> UiText.Resource(Res.string.search_trips_from)
    RouteAddressField.TO -> UiText.Resource(Res.string.search_trips_to)
}
