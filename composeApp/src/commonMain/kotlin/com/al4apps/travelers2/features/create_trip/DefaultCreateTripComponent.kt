package com.al4apps.travelers2.features.create_trip

import com.al4apps.travelers2.core.CoroutineFeature
import com.al4apps.travelers2.core.CoroutineFeatureImpl
import com.arkivanov.decompose.ComponentContext

class DefaultCreateTripComponent(
    componentContext: ComponentContext,
) : CreateTripComponent,
    ComponentContext by componentContext,
    CoroutineFeature by CoroutineFeatureImpl(componentContext)
