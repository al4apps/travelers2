package com.al4apps.travelers2.features.create_trip.route

import com.al4apps.travelers2.features.create_trip.route.model.CreateTripRouteUiState
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value

interface CreateTripRouteComponent {
    val uiState: Value<CreateTripRouteUiState>
    val stack: Value<ChildStack<RouteSubConfig, RouteSubChild>>

    fun onFromClick()
    fun onToClick()
    fun onDateClick()
    fun onContinueClick()
    fun onRecentRepeatClick(recentTripId: String)
}
