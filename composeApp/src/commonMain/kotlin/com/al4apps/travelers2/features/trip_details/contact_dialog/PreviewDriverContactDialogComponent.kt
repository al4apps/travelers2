package com.al4apps.travelers2.features.trip_details.contact_dialog

import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.al4apps.travelers2.features.trip_details.model.TripDriverContact

class PreviewDriverContactDialogComponent : DriverContactDialogComponent by DefaultDriverContactDialogComponent(
    componentContext = DefaultComponentContext(LifecycleRegistry()),
    driverName = "Искандар",
    contact = TripDriverContact(
        phoneNumber = "+992900001122",
        whatsappPhoneNumber = "+992900001122",
        telegramUsername = "iskandar_driver",
    ),
    onActionSelected = {},
    onDismiss = {},
)
