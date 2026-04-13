package com.al4apps.travelers2.features.search_results

import com.al4apps.travelers2.features.search.model.SearchQuery

interface SearchResultsComponent {
    val query: SearchQuery

    fun onBackClick()
}
