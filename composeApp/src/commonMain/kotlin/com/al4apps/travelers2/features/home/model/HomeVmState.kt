package com.al4apps.travelers2.features.home.model

import kotlinx.serialization.Serializable

@Serializable
data class HomeVmState(
    val isLoading: Boolean = true,
    val isDockVisible: Boolean = true,
    val errorMessage: String? = null,
)
