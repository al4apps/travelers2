package com.al4apps.travelers2.features.home

import kotlinx.serialization.Serializable

@Serializable
sealed interface HomeConfig {

    @Serializable
    data object SearchTrips : HomeConfig

    @Serializable
    data object CreateTrip : HomeConfig

    @Serializable
    data object Profile : HomeConfig
}
