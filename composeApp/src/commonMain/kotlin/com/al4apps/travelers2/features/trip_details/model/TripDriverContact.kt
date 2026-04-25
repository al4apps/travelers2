package com.al4apps.travelers2.features.trip_details.model

import kotlinx.serialization.Serializable

@Serializable
data class TripDriverContact(
    val phoneNumber: String,
    val whatsappPhoneNumber: String,
    val telegramUsername: String,
)
