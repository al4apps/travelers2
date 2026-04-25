package com.al4apps.travelers2.features.trip_details

import com.al4apps.travelers2.features.trip_details.contact_dialog.DriverContactDialogComponent

sealed interface TripDetailsDialogChild {
    data class DriverContact(
        val component: DriverContactDialogComponent,
    ) : TripDetailsDialogChild
}
