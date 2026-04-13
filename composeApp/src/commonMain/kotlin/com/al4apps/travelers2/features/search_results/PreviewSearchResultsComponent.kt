package com.al4apps.travelers2.features.search_results

import com.al4apps.travelers2.core.model.TripAddress
import com.al4apps.travelers2.core.model.TripDate
import com.al4apps.travelers2.features.search.model.SearchQuery

class PreviewSearchResultsComponent : SearchResultsComponent {
    override val query: SearchQuery = SearchQuery(
        fromAddress = TripAddress(
            country = "Таджикистан",
            region = "Душанбе",
            town = "Душанбе",
            locality = null,
        ),
        toAddress = TripAddress(
            country = "Таджикистан",
            region = "Согдийская область",
            town = "Худжанд",
            locality = null,
        ),
        tripDate = TripDate(year = 2026, month = 4, day = 15),
    )

    override fun onBackClick() = Unit
}
