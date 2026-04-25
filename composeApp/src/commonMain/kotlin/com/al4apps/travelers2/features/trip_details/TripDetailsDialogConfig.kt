package com.al4apps.travelers2.features.trip_details

import kotlinx.serialization.Serializable

@Serializable
sealed interface TripDetailsDialogConfig {
    @Serializable
    data object DriverContact : TripDetailsDialogConfig
}
