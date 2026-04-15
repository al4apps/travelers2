package com.al4apps.travelers2.features.search_results.model

import org.jetbrains.compose.resources.DrawableResource
import travelers2.composeapp.generated.resources.Res
import travelers2.composeapp.generated.resources.car
import travelers2.composeapp.generated.resources.car_white
import travelers2.composeapp.generated.resources.profile_circle

data class SearchResultTripUiModel(
    val id: String,
    val departureTime: String,
    val durationText: String,
    val arrivalTime: String,
    val fromAddress: String,
    val toAddress: String,
    val driverName: String,
    val priceText: String,
    val carImageRes: DrawableResource = Res.drawable.car_white,
    val driverImageRes: DrawableResource = Res.drawable.profile_circle,
    val vehicleIconRes: DrawableResource = Res.drawable.car,
)
