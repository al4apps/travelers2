package com.al4apps.travelers2.core.model

import kotlinx.serialization.Serializable

@Serializable
data class TripDate(
    val year: Int,
    val month: Int,
    val day: Int,
)
