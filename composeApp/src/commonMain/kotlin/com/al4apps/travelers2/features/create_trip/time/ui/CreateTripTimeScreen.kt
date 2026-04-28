package com.al4apps.travelers2.features.create_trip.time.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.al4apps.travelers2.core.ui.UiText
import com.al4apps.travelers2.core.ui.getString
import com.al4apps.travelers2.design_system.CounterStepper
import com.al4apps.travelers2.design_system.HomeTopBar
import com.al4apps.travelers2.design_system.button.PrimaryButton
import com.al4apps.travelers2.features.create_trip.time.CreateTripTimeComponent
import com.al4apps.travelers2.features.create_trip.time.PreviewCreateTripTimeComponent
import com.al4apps.travelers2.features.create_trip.time.TimeDialogChild
import com.al4apps.travelers2.theme.AppTheme
import com.arkivanov.decompose.extensions.compose.subscribeAsState

@Composable
fun CreateTripTimeScreen(
    component: CreateTripTimeComponent,
    bottomSpacer: @Composable () -> Unit = {},
) {
    val uiState by component.uiState.subscribeAsState()
    val dialogSlot by component.dialogSlot.subscribeAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            HomeTopBar(
                state = uiState.topBarState,
                onBackClick = component::onBackClick,
            )
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                item {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(2.dp),
                    ) {
                        Text(
                            text = uiState.title.getString(),
                            style = AppTheme.typography.h2,
                            color = AppTheme.colors.text.default,
                        )
                        Text(
                            text = uiState.subtitle.getString(),
                            style = AppTheme.typography.body2,
                            color = AppTheme.colors.text.tertiary,
                        )
                    }
                }
                item {
                    DepartureCard(
                        label = uiState.departureLabel,
                        value = uiState.departureValue,
                        placeholder = uiState.departurePlaceholder,
                        onClick = component::onDepartureClick,
                    )
                }
                item {
                    DurationCard(
                        label = uiState.durationLabel,
                        optional = uiState.durationOptional,
                        hoursLabel = uiState.durationHoursLabel,
                        minutesLabel = uiState.durationMinutesLabel,
                        hoursValue = uiState.durationHoursValue,
                        minutesValue = uiState.durationMinutesValue,
                        canDecHours = uiState.canDecrementHours,
                        canIncHours = uiState.canIncrementHours,
                        canDecMinutes = uiState.canDecrementMinutes,
                        canIncMinutes = uiState.canIncrementMinutes,
                        arrivalText = uiState.arrivalText,
                        onDecHours = component::onDecrementHours,
                        onIncHours = component::onIncrementHours,
                        onDecMinutes = component::onDecrementMinutes,
                        onIncMinutes = component::onIncrementMinutes,
                    )
                }
                item {
                    MeetingCard(
                        label = uiState.meetingLabel,
                        value = uiState.meetingValue,
                        placeholder = uiState.meetingPlaceholder,
                        onValueChange = component::onMeetingChange,
                    )
                }
                item {
                    PrimaryButton(
                        uiState = uiState.continueButtonUiState,
                        modifier = Modifier
                            .fillMaxWidth()
                            .widthIn(min = 160.dp),
                        onClick = component::onContinueClick,
                    )
                }
                item { bottomSpacer() }
            }
        }

        when (val child = dialogSlot.child?.instance) {
            is TimeDialogChild.TimePicker -> TimePickerDialog(
                child = child,
            )
            null -> Unit
        }
    }
}

@Composable
private fun DepartureCard(
    label: UiText,
    value: String,
    placeholder: UiText,
    onClick: () -> Unit,
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = AppTheme.colors.background.default,
        ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 14.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Text(
                text = label.getString(),
                style = AppTheme.typography.caption,
                color = AppTheme.colors.text.tertiary,
            )
            Text(
                text = value.ifEmpty { placeholder.getString() },
                style = AppTheme.typography.h2,
                color = if (value.isEmpty()) {
                    AppTheme.colors.text.tertiary
                } else {
                    AppTheme.colors.text.default
                },
            )
        }
    }
}

@Composable
private fun DurationCard(
    label: UiText,
    optional: UiText,
    hoursLabel: UiText,
    minutesLabel: UiText,
    hoursValue: String,
    minutesValue: String,
    canDecHours: Boolean,
    canIncHours: Boolean,
    canDecMinutes: Boolean,
    canIncMinutes: Boolean,
    arrivalText: UiText,
    onDecHours: () -> Unit,
    onIncHours: () -> Unit,
    onDecMinutes: () -> Unit,
    onIncMinutes: () -> Unit,
) {
    ElevatedCard(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = AppTheme.colors.background.default,
        ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 14.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Text(
                    text = label.getString(),
                    style = AppTheme.typography.subtitle,
                    color = AppTheme.colors.text.default,
                )
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(AppTheme.colors.background.gray)
                        .padding(horizontal = 6.dp, vertical = 2.dp),
                ) {
                    Text(
                        text = optional.getString(),
                        style = AppTheme.typography.caption,
                        color = AppTheme.colors.text.tertiary,
                    )
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                StepperBlock(
                    label = hoursLabel.getString(),
                    value = hoursValue,
                    canDec = canDecHours,
                    canInc = canIncHours,
                    onDec = onDecHours,
                    onInc = onIncHours,
                    modifier = Modifier.weight(1f),
                )
                StepperBlock(
                    label = minutesLabel.getString(),
                    value = minutesValue,
                    canDec = canDecMinutes,
                    canInc = canIncMinutes,
                    onDec = onDecMinutes,
                    onInc = onIncMinutes,
                    modifier = Modifier.weight(1f),
                )
            }
            Text(
                text = arrivalText.getString(),
                style = AppTheme.typography.body2,
                color = AppTheme.colors.text.tertiary,
            )
        }
    }
}

@Composable
private fun StepperBlock(
    label: String,
    value: String,
    canDec: Boolean,
    canInc: Boolean,
    onDec: () -> Unit,
    onInc: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(14.dp))
            .background(AppTheme.colors.background.gray)
            .padding(horizontal = 12.dp, vertical = 10.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = label.uppercase(),
            style = AppTheme.typography.caption,
            color = AppTheme.colors.text.tertiary,
        )
        CounterStepper(
            value = value,
            canDecrement = canDec,
            canIncrement = canInc,
            onDecrement = onDec,
            onIncrement = onInc,
        )
    }
}

@Composable
private fun MeetingCard(
    label: UiText,
    value: String,
    placeholder: UiText,
    onValueChange: (String) -> Unit,
) {
    ElevatedCard(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = AppTheme.colors.background.default,
        ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 14.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(
                text = label.getString(),
                style = AppTheme.typography.subtitle,
                color = AppTheme.colors.text.default,
            )
            OutlinedTextField(
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                placeholder = {
                    Text(
                        text = placeholder.getString(),
                        style = AppTheme.typography.body1,
                        color = AppTheme.colors.text.tertiary,
                    )
                },
                shape = RoundedCornerShape(14.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = AppTheme.colors.border.secondary,
                    unfocusedBorderColor = AppTheme.colors.border.divider,
                ),
            )
            Spacer(modifier = Modifier.height(2.dp))
        }
    }
}

@Preview
@Composable
private fun CreateTripTimeScreenPreview() {
    CreateTripTimeScreen(component = PreviewCreateTripTimeComponent())
}
