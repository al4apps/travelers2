package com.al4apps.travelers2.features.search_results.model

import com.al4apps.travelers2.features.search.model.SearchQuery
import kotlinx.serialization.Serializable

@Serializable
data class SearchResultsVmState(
    val query: SearchQuery,
    val selectedTab: SearchResultsTab = SearchResultsTab.ALL,
    val passengerCount: Int = 1,
    val trips: List<SearchResultTripVmModel> = emptyList(),
)
