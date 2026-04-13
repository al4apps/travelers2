package com.al4apps.travelers2.features.search_results

import com.al4apps.travelers2.core.CoroutineFeature
import com.al4apps.travelers2.core.CoroutineFeatureImpl
import com.al4apps.travelers2.features.search.model.SearchQuery
import com.arkivanov.decompose.ComponentContext

class DefaultSearchResultsComponent(
    componentContext: ComponentContext,
    override val query: SearchQuery,
    private val onBack: () -> Unit,
) : SearchResultsComponent,
    ComponentContext by componentContext,
    CoroutineFeature by CoroutineFeatureImpl(componentContext) {

    override fun onBackClick() {
        onBack.invoke()
    }
}
