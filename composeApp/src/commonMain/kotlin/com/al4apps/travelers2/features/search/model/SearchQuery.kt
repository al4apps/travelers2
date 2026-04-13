package com.al4apps.travelers2.features.search.model

import kotlinx.serialization.Serializable

@Serializable
data class SearchQuery(
    val fromAddress: String,
    val toAddress: String,
    val tripDate: String,
)
