package com.al4apps.travelers2.features.root

import kotlinx.serialization.Serializable

@Serializable
sealed interface RootConfig {
    @Serializable
    data object Home : RootConfig

    @Serializable
    data object Onboarding : RootConfig
}
