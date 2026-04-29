package com.al4apps.travelers2.features.create_trip.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.al4apps.travelers2.core.ui.UiText
import com.al4apps.travelers2.core.ui.getString
import com.al4apps.travelers2.features.create_trip.CreateTripChild
import com.al4apps.travelers2.features.create_trip.CreateTripComponent
import com.al4apps.travelers2.features.create_trip.CreateTripDialogChild
import com.al4apps.travelers2.features.create_trip.PreviewCreateTripComponent
import com.al4apps.travelers2.features.create_trip.confirm.ui.CreateTripConfirmScreen
import com.al4apps.travelers2.features.create_trip.route.ui.CreateTripRouteScreen
import com.al4apps.travelers2.features.create_trip.time.ui.CreateTripTimeScreen
import com.al4apps.travelers2.features.create_trip.vehicle.ui.CreateTripVehicleScreen
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import travelers2.composeapp.generated.resources.Res
import travelers2.composeapp.generated.resources.create_trip_confirm_published_message

@Composable
fun CreateTripScreen(
    component: CreateTripComponent,
    bottomSpacer: @Composable () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val dialogSlot by component.dialogSlot.subscribeAsState()
    val publishedMessage =
        UiText.Resource(Res.string.create_trip_confirm_published_message).getString()

    LaunchedEffect(dialogSlot.child?.instance) {
        when (dialogSlot.child?.instance) {
            CreateTripDialogChild.Published -> {
                snackbarHostState.showSnackbar(
                    message = publishedMessage,
                    duration = SnackbarDuration.Short,
                )
                component.onPublishedAcknowledged()
            }
            null -> Unit
        }
    }

    Box(modifier = modifier.fillMaxSize()) {
        Children(stack = component.stack) { child ->
            when (val instance = child.instance) {
                is CreateTripChild.Route -> CreateTripRouteScreen(
                    component = instance.component,
                    bottomSpacer = bottomSpacer,
                )
                is CreateTripChild.Time -> CreateTripTimeScreen(
                    component = instance.component,
                    bottomSpacer = bottomSpacer,
                )
                is CreateTripChild.Vehicle -> CreateTripVehicleScreen(
                    component = instance.component,
                    bottomSpacer = bottomSpacer,
                )
                is CreateTripChild.Confirm -> CreateTripConfirmScreen(
                    component = instance.component,
                    bottomSpacer = bottomSpacer,
                )
            }
        }
        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier.align(Alignment.BottomCenter),
        )
    }
}

@Preview
@Composable
private fun CreateTripScreenPreview() {
    CreateTripScreen(component = PreviewCreateTripComponent())
}
