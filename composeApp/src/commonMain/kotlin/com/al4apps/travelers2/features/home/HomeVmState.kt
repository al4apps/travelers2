package com.al4apps.travelers2.features.home

import kotlinx.serialization.Serializable

@Serializable
data class HomeVmState(
    val isLoading: Boolean = true,
    val title: String = "",
    val message: String = "",
    val errorMessage: String? = null,
)
