package com.al4apps.travelers2.features.search.model

import com.al4apps.travelers2.core.model.TripAddress
import com.al4apps.travelers2.core.model.TripDate
import kotlinx.serialization.Serializable

@Serializable
data class SearchQuery(
    val fromAddress: TripAddress?,
    val toAddress: TripAddress?,
    val tripDate: TripDate?,
)
