package com.al4apps.travelers2.features.create_trip.model

import kotlinx.serialization.Serializable

@Serializable
data class Vehicle(
    val id: String,
    val model: String,
    val plateNumber: String,
    val totalSeats: Int,
)
