package com.al4apps.travelers2.features.create_trip.route.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.al4apps.travelers2.core.ui.UiText
import com.al4apps.travelers2.core.ui.getString
import com.al4apps.travelers2.design_system.RecentTripCard
import com.al4apps.travelers2.design_system.button.PrimaryButton
import com.al4apps.travelers2.design_system.input.TravelersFormTextField
import com.al4apps.travelers2.features.create_trip.route.CreateTripRouteComponent
import com.al4apps.travelers2.features.create_trip.route.PreviewCreateTripRouteComponent
import com.al4apps.travelers2.features.create_trip.route.RouteSubChild
import com.al4apps.travelers2.features.create_trip.route.model.CreateTripRouteUiState
import com.al4apps.travelers2.features.search_form.address_search.ui.AddressSearchScreen
import com.al4apps.travelers2.features.search_form.calendar.ui.CalendarContent
import com.al4apps.travelers2.shared_model.ButtonUiState
import com.al4apps.travelers2.theme.AppTheme
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import travelers2.composeapp.generated.resources.Res
import travelers2.composeapp.generated.resources.calendar
import travelers2.composeapp.generated.resources.pin

@Composable
fun CreateTripRouteScreen(
    component: CreateTripRouteComponent,
    bottomSpacer: @Composable () -> Unit = {},
) {
    val uiState by component.uiState.subscribeAsState()

    Children(stack = component.stack) { child ->
        when (val instance = child.instance) {
            RouteSubChild.Form -> RouteRootContent(
                uiState = uiState,
                onFromClick = component::onFromClick,
                onToClick = component::onToClick,
                onDateClick = component::onDateClick,
                onContinueClick = component::onContinueClick,
                onRepeatClick = component::onRecentRepeatClick,
                bottomSpacer = bottomSpacer,
            )

            is RouteSubChild.AddressSearch -> AddressSearchScreen(
                component = instance.component,
                modifier = Modifier.fillMaxSize(),
            )

            is RouteSubChild.Calendar -> Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = 16.dp),
            ) {
                CalendarContent(component = instance.component)
            }
        }
    }
}

@Composable
private fun RouteRootContent(
    uiState: CreateTripRouteUiState,
    onFromClick: () -> Unit,
    onToClick: () -> Unit,
    onDateClick: () -> Unit,
    onContinueClick: () -> Unit,
    onRepeatClick: (recentTripId: String) -> Unit,
    bottomSpacer: @Composable () -> Unit,
) {
    when (uiState.mode) {
        CreateTripRouteUiState.Mode.UNAUTHENTICATED -> AuthRequiredContent(
            title = uiState.authPromptTitle,
            subtitle = uiState.authPromptSubtitle,
            bottomSpacer = bottomSpacer,
        )

        CreateTripRouteUiState.Mode.AUTHENTICATED -> AuthenticatedContent(
            uiState = uiState,
            onFromClick = onFromClick,
            onToClick = onToClick,
            onDateClick = onDateClick,
            onContinueClick = onContinueClick,
            onRepeatClick = onRepeatClick,
            bottomSpacer = bottomSpacer,
        )
    }
}

@Composable
private fun AuthRequiredContent(
    title: UiText,
    subtitle: UiText,
    bottomSpacer: @Composable () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(
                text = title.getString(),
                style = AppTheme.typography.h2,
                color = AppTheme.colors.text.default,
                textAlign = TextAlign.Center,
            )
            Text(
                text = subtitle.getString(),
                style = AppTheme.typography.body1,
                color = AppTheme.colors.text.tertiary,
                textAlign = TextAlign.Center,
            )
        }
    }
    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.weight(1f))
        bottomSpacer()
    }
}

@Composable
private fun AuthenticatedContent(
    uiState: CreateTripRouteUiState,
    onFromClick: () -> Unit,
    onToClick: () -> Unit,
    onDateClick: () -> Unit,
    onContinueClick: () -> Unit,
    onRepeatClick: (recentTripId: String) -> Unit,
    bottomSpacer: @Composable () -> Unit,
) {
    val recentRepeatLabel = uiState.recentRepeatLabel.getString()

    Column(modifier = Modifier.fillMaxSize()) {
        // Top: fixed (non-scrollable) form card.
        Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)) {
            FormCard(
                title = uiState.title,
                fromValue = uiState.fromValue,
                toValue = uiState.toValue,
                dateValue = uiState.dateValue,
                fromPlaceholder = uiState.fromPlaceholder,
                toPlaceholder = uiState.toPlaceholder,
                datePlaceholder = uiState.datePlaceholder,
                buttonUiState = uiState.continueButtonUiState,
                onFromClick = onFromClick,
                onToClick = onToClick,
                onDateClick = onDateClick,
                onContinueClick = onContinueClick,
            )
        }

        // Bottom: scrollable recent trips section (or empty banner).
        if (uiState.recentTrips.isNotEmpty()) {
            Text(
                text = uiState.recentSectionTitle.getString(),
                style = AppTheme.typography.subtitle,
                color = AppTheme.colors.text.default,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                contentPadding = PaddingValues(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                items(items = uiState.recentTrips, key = { it.id }) { recent ->
                    RecentTripCard(
                        state = recent,
                        repeatLabel = recentRepeatLabel,
                        onRepeatClick = { onRepeatClick(recent.id) },
                    )
                }
                item { bottomSpacer() }
            }
        } else {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                contentAlignment = Alignment.Center,
            ) {
                EmptyBanner(
                    title = uiState.recentEmptyTitle,
                    subtitle = uiState.recentEmptySubtitle,
                )
            }
            bottomSpacer()
        }
    }
}

@Composable
private fun FormCard(
    title: UiText,
    fromValue: String,
    toValue: String,
    dateValue: String,
    fromPlaceholder: UiText,
    toPlaceholder: UiText,
    datePlaceholder: UiText,
    buttonUiState: ButtonUiState,
    onFromClick: () -> Unit,
    onToClick: () -> Unit,
    onDateClick: () -> Unit,
    onContinueClick: () -> Unit,
) {
    ElevatedCard(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = AppTheme.colors.background.default,
        ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = title.getString(),
                style = AppTheme.typography.h2,
                color = AppTheme.colors.text.default,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp),
            )
            TravelersFormTextField(
                value = fromValue,
                placeholder = fromPlaceholder,
                icon = Res.drawable.pin,
                enabled = false,
                readOnly = true,
                onClick = onFromClick,
                onValueChange = {},
            )
            TravelersFormTextField(
                value = toValue,
                placeholder = toPlaceholder,
                icon = Res.drawable.pin,
                enabled = false,
                readOnly = true,
                onClick = onToClick,
                onValueChange = {},
            )
            TravelersFormTextField(
                value = dateValue,
                placeholder = datePlaceholder,
                icon = Res.drawable.calendar,
                enabled = false,
                readOnly = true,
                onClick = onDateClick,
                onValueChange = {},
            )
            Spacer(modifier = Modifier.height(4.dp))
            PrimaryButton(
                uiState = buttonUiState,
                modifier = Modifier.widthIn(min = 160.dp),
                onClick = onContinueClick,
            )
        }
    }
}

@Composable
private fun EmptyBanner(
    title: UiText,
    subtitle: UiText,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(AppTheme.colors.background.secondary)
            .padding(horizontal = 20.dp, vertical = 28.dp),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            Text(
                text = title.getString(),
                style = AppTheme.typography.subtitle,
                color = AppTheme.colors.text.default,
                textAlign = TextAlign.Center,
            )
            Text(
                text = subtitle.getString(),
                style = AppTheme.typography.body2,
                color = AppTheme.colors.text.tertiary,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Preview
@Composable
private fun CreateTripRouteScreenPreview() {
    CreateTripRouteScreen(component = PreviewCreateTripRouteComponent())
}
