package com.al4apps.travelers2.features.search_results.model

import kotlinx.serialization.Serializable

@Serializable
data class SearchResultTripVmModel(
    val id: String,
    val departureTime: String,
    val durationText: String,
    val arrivalTime: String,
    val fromAddress: String,
    val toAddress: String,
    val driverName: String,
    val priceText: String,
)
