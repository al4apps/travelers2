package com.al4apps.travelers2.features.create_trip

import com.al4apps.travelers2.features.create_trip.route.PreviewCreateTripRouteComponent
import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value

class PreviewCreateTripComponent : CreateTripComponent {
    override val stack: Value<ChildStack<CreateTripConfig, CreateTripChild>> = MutableValue(
        ChildStack(
            configuration = CreateTripConfig.Route,
            instance = CreateTripChild.Route(component = PreviewCreateTripRouteComponent()),
        ),
    )

    override val dialogSlot: Value<ChildSlot<CreateTripDialogConfig, CreateTripDialogChild>> =
        MutableValue(ChildSlot())

    override fun onPublishedAcknowledged() = Unit
}
