package com.al4apps.travelers2.features.search_form.model

import com.al4apps.travelers2.core.model.TripAddress
import com.al4apps.travelers2.core.model.TripDate
import kotlinx.serialization.Serializable

@Serializable
data class SearchTripsVmState(
    val fromAddress: TripAddress? = null,
    val toAddress: TripAddress? = null,
    val tripDate: TripDate? = null,
)
