package com.al4apps.travelers2.features.search

import com.al4apps.travelers2.features.search_results.SearchResultsComponent
import com.al4apps.travelers2.features.search_trips.SearchFormComponent

sealed interface SearchChild {
    data class Form(
        val component: SearchFormComponent,
    ) : SearchChild

    data class Results(
        val component: SearchResultsComponent,
    ) : SearchChild
}
