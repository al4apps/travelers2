package com.al4apps.travelers2.features.home.profile

import com.al4apps.travelers2.core.CoroutineFeature
import com.al4apps.travelers2.core.CoroutineFeatureImpl
import com.arkivanov.decompose.ComponentContext

class DefaultProfileComponent(
    componentContext: ComponentContext,
) : ProfileComponent,
    ComponentContext by componentContext,
    CoroutineFeature by CoroutineFeatureImpl(componentContext)
