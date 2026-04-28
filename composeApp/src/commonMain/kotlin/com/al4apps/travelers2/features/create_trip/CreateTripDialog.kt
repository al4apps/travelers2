package com.al4apps.travelers2.features.create_trip

import kotlinx.serialization.Serializable

@Serializable
sealed interface CreateTripDialogConfig {
    @Serializable
    data object Published : CreateTripDialogConfig
}

sealed interface CreateTripDialogChild {
    data object Published : CreateTripDialogChild
}
