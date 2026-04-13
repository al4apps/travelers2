package com.al4apps.travelers2.features.search

import com.al4apps.travelers2.core.model.TripAddress
import com.al4apps.travelers2.core.model.TripDate
import com.al4apps.travelers2.core.CoroutineFeature
import com.al4apps.travelers2.core.CoroutineFeatureImpl
import com.al4apps.travelers2.features.search.model.SearchQuery
import com.al4apps.travelers2.features.search_results.DefaultSearchResultsComponent
import com.al4apps.travelers2.features.search_trips.DefaultSearchFormComponent
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.pushNew
import com.arkivanov.decompose.value.Value

class DefaultSearchFeatureComponent(
    componentContext: ComponentContext,
) : SearchFeatureComponent,
    ComponentContext by componentContext,
    CoroutineFeature by CoroutineFeatureImpl(componentContext) {

    private val navigation = StackNavigation<SearchConfig>()

    override val stack: Value<ChildStack<SearchConfig, SearchChild>> = childStack(
        source = navigation,
        serializer = SearchConfig.serializer(),
        initialConfiguration = SearchConfig.Form,
        handleBackButton = true,
        childFactory = ::createChild,
    )

    private fun createChild(
        config: SearchConfig,
        componentContext: ComponentContext,
    ): SearchChild =
        when (config) {
            SearchConfig.Form -> SearchChild.Form(
                component = DefaultSearchFormComponent(
                    componentContext = componentContext,
                    onSearchRequested = ::onSearchRequested,
                ),
            )

            is SearchConfig.Results -> SearchChild.Results(
                component = DefaultSearchResultsComponent(
                    componentContext = componentContext,
                    query = config.query,
                    onBack = { navigation.pop() },
                ),
            )
        }

    private fun onSearchRequested(
        fromAddress: TripAddress?,
        toAddress: TripAddress?,
        tripDate: TripDate?,
    ) {
        navigation.pushNew(
            SearchConfig.Results(
                query = SearchQuery(
                    fromAddress = fromAddress,
                    toAddress = toAddress,
                    tripDate = tripDate,
                ),
            ),
        )
    }
}
