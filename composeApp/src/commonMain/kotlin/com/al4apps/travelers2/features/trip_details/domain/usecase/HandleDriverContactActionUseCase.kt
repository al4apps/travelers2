package com.al4apps.travelers2.features.trip_details.domain.usecase

import com.al4apps.travelers2.features.trip_details.contact_dialog.DriverContactAction
import com.al4apps.travelers2.features.trip_details.domain.repository.DriverContactRepository
import com.al4apps.travelers2.features.trip_details.model.TripDriverContact

class HandleDriverContactActionUseCase(
    private val driverContactRepository: DriverContactRepository,
) {

    /**
     * Delegates the selected contact action to the driver contact repository.
     * Returns `true` when the external handoff was accepted by the platform.
     */
    operator fun invoke(
        action: DriverContactAction,
        contact: TripDriverContact,
    ): Boolean = driverContactRepository.contactDriver(
        action = action,
        contact = contact,
    )
}
