package com.al4apps.travelers2.features.create_trip.route

import com.al4apps.travelers2.features.create_trip.route.map.CreateTripRouteUiAssembler
import com.al4apps.travelers2.features.create_trip.route.model.CreateTripRouteUiState
import com.al4apps.travelers2.features.create_trip.route.model.CreateTripRouteVmState
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value

class PreviewCreateTripRouteComponent : CreateTripRouteComponent {
    override val uiState: Value<CreateTripRouteUiState> = MutableValue(
        CreateTripRouteUiAssembler().assemble(
            vmState = CreateTripRouteVmState(),
            recentTrips = emptyList(),
        ),
    )
    override val stack: Value<ChildStack<RouteSubConfig, RouteSubChild>> = MutableValue(
        ChildStack(
            configuration = RouteSubConfig.Form,
            instance = RouteSubChild.Form,
        ),
    )

    override fun onFromClick() = Unit
    override fun onToClick() = Unit
    override fun onDateClick() = Unit
    override fun onContinueClick() = Unit
    override fun onRecentRepeatClick(recentTripId: String) = Unit
}
