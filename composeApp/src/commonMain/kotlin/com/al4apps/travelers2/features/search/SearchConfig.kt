package com.al4apps.travelers2.features.search

import com.al4apps.travelers2.features.search.model.SearchQuery
import kotlinx.serialization.Serializable

@Serializable
sealed interface SearchConfig {
    @Serializable
    data object Form : SearchConfig

    @Serializable
    data class Results(
        val query: SearchQuery,
    ) : SearchConfig

    @Serializable
    data class TripDetails(
        val tripId: String,
    ) : SearchConfig
}
