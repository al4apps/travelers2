package com.al4apps.travelers2.features.trip_details.data

import com.al4apps.travelers2.core.platform.ContactAppLauncher
import com.al4apps.travelers2.features.trip_details.contact_dialog.DriverContactAction
import com.al4apps.travelers2.features.trip_details.domain.repository.DriverContactRepository
import com.al4apps.travelers2.features.trip_details.model.TripDriverContact

class PlatformDriverContactRepository(
    private val contactAppLauncher: ContactAppLauncher,
) : DriverContactRepository {

    /**
     * Opens the selected contact channel for the provided driver contact details.
     * Returns `true` when the platform accepted the request, otherwise `false`.
     */
    override fun contactDriver(
        action: DriverContactAction,
        contact: TripDriverContact,
    ): Boolean =
        when (action) {
            DriverContactAction.PHONE -> contactAppLauncher.openDialer(contact.phoneNumber)
            DriverContactAction.WHATSAPP -> contactAppLauncher.openWhatsApp(contact.whatsappPhoneNumber)
            DriverContactAction.TELEGRAM -> contactAppLauncher.openTelegram(contact.telegramUsername)
        }
}
