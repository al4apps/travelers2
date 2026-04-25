package com.al4apps.travelers2.features.trip_details.contact_dialog.map

import com.al4apps.travelers2.core.dialog.BaseContentDialogUiState
import com.al4apps.travelers2.core.dialog.DialogAlign
import com.al4apps.travelers2.core.dialog.DialogBackground
import com.al4apps.travelers2.core.dialog.DialogContentState
import com.al4apps.travelers2.core.ui.UiText
import com.al4apps.travelers2.features.trip_details.contact_dialog.DriverContactAction
import com.al4apps.travelers2.features.trip_details.contact_dialog.model.DriverContactActionStyle
import com.al4apps.travelers2.features.trip_details.contact_dialog.model.DriverContactActionUiState
import com.al4apps.travelers2.features.trip_details.contact_dialog.model.DriverContactDialogUiState
import com.al4apps.travelers2.features.trip_details.model.TripDriverContact
import travelers2.composeapp.generated.resources.Res
import travelers2.composeapp.generated.resources.trip_details_contact_dialog_phone
import travelers2.composeapp.generated.resources.trip_details_contact_dialog_subtitle
import travelers2.composeapp.generated.resources.trip_details_contact_dialog_telegram
import travelers2.composeapp.generated.resources.trip_details_contact_dialog_title
import travelers2.composeapp.generated.resources.trip_details_contact_dialog_whatsapp

class DriverContactDialogUiAssembler {

    fun assembleDialogContentState(): DialogContentState = DialogContentState(
        background = DialogBackground.DIMMED,
        align = DialogAlign.CENTER,
        isDismissAllow = true,
    )

    fun assembleBaseContentState(driverName: String): BaseContentDialogUiState = BaseContentDialogUiState(
        title = UiText.Resource(Res.string.trip_details_contact_dialog_title),
        text = UiText.Resource(
            value = Res.string.trip_details_contact_dialog_subtitle,
            args = listOf(driverName),
        ),
    )

    fun assembleContactActionsState(contact: TripDriverContact): DriverContactDialogUiState =
        DriverContactDialogUiState(
            actions = buildList {
                add(
                    DriverContactActionUiState(
                        action = DriverContactAction.PHONE,
                        title = UiText.Resource(Res.string.trip_details_contact_dialog_phone),
                        style = DriverContactActionStyle.PHONE,
                    ),
                )

                if (contact.whatsappPhoneNumber.isNotBlank()) {
                    add(
                        DriverContactActionUiState(
                            action = DriverContactAction.WHATSAPP,
                            title = UiText.Resource(Res.string.trip_details_contact_dialog_whatsapp),
                            style = DriverContactActionStyle.WHATSAPP,
                        ),
                    )
                }

                if (contact.telegramUsername.isNotBlank()) {
                    add(
                        DriverContactActionUiState(
                            action = DriverContactAction.TELEGRAM,
                            title = UiText.Resource(Res.string.trip_details_contact_dialog_telegram),
                            style = DriverContactActionStyle.TELEGRAM,
                        ),
                    )
                }
            },
        )
}
