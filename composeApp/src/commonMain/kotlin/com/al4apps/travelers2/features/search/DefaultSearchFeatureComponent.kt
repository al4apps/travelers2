package com.al4apps.travelers2.features.search

import com.al4apps.travelers2.core.CoroutineFeature
import com.al4apps.travelers2.core.CoroutineFeatureImpl
import com.al4apps.travelers2.core.model.TripAddress
import com.al4apps.travelers2.core.model.TripDate
import com.al4apps.travelers2.features.search.model.SearchQuery
import com.al4apps.travelers2.features.search_form.DefaultSearchFormComponent
import com.al4apps.travelers2.features.search_results.DefaultSearchResultsComponent
import com.al4apps.travelers2.features.trip_details.domain.usecase.HandleDriverContactActionUseCase
import com.al4apps.travelers2.features.trip_details.DefaultTripDetailsComponent
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.pushNew
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.lifecycle.doOnDestroy

class DefaultSearchFeatureComponent(
    componentContext: ComponentContext,
    private val updateDockVisibility: (isVisible: Boolean) -> Unit,
    private val handleDriverContactActionUseCase: HandleDriverContactActionUseCase,
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

    init {
        subscribeStack()
    }

    @OptIn(ExperimentalDecomposeApi::class)
    private fun subscribeStack() {
        val cancellation = stack.subscribe { childStack ->
            updateDockVisibility(childStack.active.configuration !is SearchConfig.TripDetails)
        }
        doOnDestroy {
            cancellation.cancel()
        }
    }

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
                    onTripClick = ::onTripClick,
                ),
            )

            is SearchConfig.TripDetails -> SearchChild.TripDetails(
                component = DefaultTripDetailsComponent(
                    componentContext = componentContext,
                    tripId = config.tripId,
                    onBack = { navigation.pop() },
                    handleDriverContactActionUseCase = handleDriverContactActionUseCase,
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

    private fun onTripClick(tripId: String) {

        navigation.pushNew(
            SearchConfig.TripDetails(tripId = tripId),
        )
    }
}
