package com.al4apps.travelers2.features.trip_details.contact_dialog

import com.al4apps.travelers2.core.CoroutineFeature
import com.al4apps.travelers2.core.CoroutineFeatureImpl
import com.al4apps.travelers2.core.dialog.BaseContentDialogUiState
import com.al4apps.travelers2.core.dialog.DialogContentState
import com.al4apps.travelers2.features.trip_details.contact_dialog.map.DriverContactDialogUiAssembler
import com.al4apps.travelers2.features.trip_details.contact_dialog.model.DriverContactDialogUiState
import com.al4apps.travelers2.features.trip_details.model.TripDriverContact
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value

class DefaultDriverContactDialogComponent(
    componentContext: ComponentContext,
    private val driverName: String,
    private val contact: TripDriverContact,
    private val onActionSelected: (DriverContactAction) -> Unit,
    private val onDismiss: () -> Unit,
    uiAssembler: DriverContactDialogUiAssembler = DriverContactDialogUiAssembler(),
) : DriverContactDialogComponent,
    ComponentContext by componentContext,
    CoroutineFeature by CoroutineFeatureImpl(componentContext) {

    override val dialogContentState: Value<DialogContentState> =
        MutableValue(uiAssembler.assembleDialogContentState())

    override val uiState: Value<BaseContentDialogUiState> =
        MutableValue(
            uiAssembler.assembleBaseContentState(
                driverName = driverName,
            ),
        )

    override val contactActionsState: Value<DriverContactDialogUiState> =
        MutableValue(
            uiAssembler.assembleContactActionsState(
                contact = contact,
            ),
        )

    override fun onPrimaryClick() = Unit

    override fun onSecondaryClick() = Unit

    override fun onDismissRequest() {
        onDismiss.invoke()
    }

    override fun onActionClick(action: DriverContactAction) {
        onActionSelected.invoke(action)
    }
}
