package com.al4apps.travelers2.features.onboarding.model

import kotlinx.serialization.Serializable

@Serializable
data class OnboardingVmState(
    val title: String = "",
    val message: String = "",
    val buttonLabel: String = "",
)
