package com.al4apps.travelers2.features.home.search_trips

import com.al4apps.travelers2.core.CoroutineFeature
import com.al4apps.travelers2.core.CoroutineFeatureImpl
import com.arkivanov.decompose.ComponentContext

class DefaultSearchTripsComponent(
    componentContext: ComponentContext,
) : SearchTripsComponent,
    ComponentContext by componentContext,
    CoroutineFeature by CoroutineFeatureImpl(componentContext)
