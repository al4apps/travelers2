package com.al4apps.travelers2.features.search_results.model

import com.al4apps.travelers2.design_system.SearchResultsTopBarUiState
import com.al4apps.travelers2.shared_model.DockUiState

data class SearchResultsUiState(
    val topBar: SearchResultsTopBarUiState,
    val tabs: DockUiState,
    val tripCards: List<SearchResultTripUiModel>,
)
