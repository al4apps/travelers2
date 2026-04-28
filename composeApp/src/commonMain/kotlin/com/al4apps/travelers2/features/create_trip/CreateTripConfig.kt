package com.al4apps.travelers2.features.create_trip

import kotlinx.serialization.Serializable

@Serializable
sealed interface CreateTripConfig {
    @Serializable
    data object Route : CreateTripConfig

    @Serializable
    data object Time : CreateTripConfig

    @Serializable
    data object Vehicle : CreateTripConfig

    @Serializable
    data object Confirm : CreateTripConfig
}
