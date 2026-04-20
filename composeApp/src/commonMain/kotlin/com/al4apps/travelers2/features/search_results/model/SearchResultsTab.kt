package com.al4apps.travelers2.features.search_results.model

import kotlinx.serialization.Serializable

@Serializable
enum class SearchResultsTab(
    val id: Long,
) {
    ALL(1L),
    CARS(2L),
    BUSES(3L),
}
