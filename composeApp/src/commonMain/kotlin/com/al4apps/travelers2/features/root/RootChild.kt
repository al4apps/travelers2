package com.al4apps.travelers2.features.root

import com.al4apps.travelers2.features.home.HomeComponent
import com.al4apps.travelers2.features.onboarding.OnboardingComponent

sealed interface RootChild {
    data class Home(
        val component: HomeComponent,
    ) : RootChild

    data class Onboarding(
        val component: OnboardingComponent,
    ) : RootChild
}
