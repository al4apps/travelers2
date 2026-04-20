package com.al4apps.travelers2.features.search_results

import com.al4apps.travelers2.features.search_results.model.SearchResultsUiState
import com.arkivanov.decompose.value.Value

interface SearchResultsComponent {
    val uiState: Value<SearchResultsUiState>

    fun onBackClick()

    fun onTabClick(tabId: Long)
}
