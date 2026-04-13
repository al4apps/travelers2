package com.al4apps.travelers2.core.model

import kotlinx.serialization.Serializable

@Serializable
data class TripAddress(
    val country: String,
    val region: String,
    val town: String,
    val locality: String? = null,
)
