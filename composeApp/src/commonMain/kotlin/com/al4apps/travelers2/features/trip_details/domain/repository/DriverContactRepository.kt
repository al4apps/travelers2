package com.al4apps.travelers2.features.trip_details.domain.repository

import com.al4apps.travelers2.features.trip_details.contact_dialog.DriverContactAction
import com.al4apps.travelers2.features.trip_details.model.TripDriverContact

interface DriverContactRepository {

    /**
     * Opens the selected driver contact channel using platform-specific capabilities.
     * Returns `true` if the handoff was initiated successfully, otherwise `false`.
     */
    fun contactDriver(
        action: DriverContactAction,
        contact: TripDriverContact,
    ): Boolean
}
