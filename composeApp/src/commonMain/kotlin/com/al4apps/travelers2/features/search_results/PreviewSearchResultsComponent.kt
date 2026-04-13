package com.al4apps.travelers2.features.search_results

import com.al4apps.travelers2.features.search.model.SearchQuery

class PreviewSearchResultsComponent : SearchResultsComponent {
    override val query: SearchQuery = SearchQuery(
        fromAddress = "Душанбе",
        toAddress = "Худжанд",
        tripDate = "15.04.2026",
    )

    override fun onBackClick() = Unit
}
