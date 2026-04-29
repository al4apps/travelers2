package com.al4apps.travelers2.features.create_trip

import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value

interface CreateTripComponent {
    val stack: Value<ChildStack<CreateTripConfig, CreateTripChild>>
    val dialogSlot: Value<ChildSlot<CreateTripDialogConfig, CreateTripDialogChild>>

    fun onPublishedAcknowledged()
}
