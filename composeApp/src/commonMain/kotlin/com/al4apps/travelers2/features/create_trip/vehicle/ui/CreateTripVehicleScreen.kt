package com.al4apps.travelers2.features.create_trip.vehicle.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.al4apps.travelers2.core.ui.UiText
import com.al4apps.travelers2.core.ui.getString
import com.al4apps.travelers2.design_system.CounterStepper
import com.al4apps.travelers2.design_system.HomeTopBar
import com.al4apps.travelers2.design_system.SettingToggleRow
import com.al4apps.travelers2.design_system.VehicleCarouselCard
import com.al4apps.travelers2.design_system.button.PrimaryButton
import com.al4apps.travelers2.features.create_trip.vehicle.CreateTripVehicleComponent
import com.al4apps.travelers2.features.create_trip.vehicle.PreviewCreateTripVehicleComponent
import com.al4apps.travelers2.theme.AppTheme
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import travelers2.composeapp.generated.resources.Res
import travelers2.composeapp.generated.resources.ic_folder
import travelers2.composeapp.generated.resources.ic_sdcard

@Composable
fun CreateTripVehicleScreen(
    component: CreateTripVehicleComponent,
    bottomSpacer: @Composable () -> Unit = {},
) {
    val uiState by component.uiState.subscribeAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        HomeTopBar(
            state = uiState.topBarState,
            onBackClick = component::onBackClick,
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
        ) {
            when {
                uiState.isLoading -> Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    CircularProgressIndicator()
                }

                uiState.errorMessage != null -> Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = uiState.errorMessage.orEmpty(),
                        style = AppTheme.typography.body1,
                        color = AppTheme.colors.text.tertiary,
                    )
                }

                else -> LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
                    verticalArrangement = Arrangement.spacedBy(14.dp),
                ) {
                    item {
                        Text(
                            text = uiState.sectionTitle.getString(),
                            style = AppTheme.typography.subtitle,
                            color = AppTheme.colors.text.default,
                        )
                    }
                    item {
                        LazyRow(
                            contentPadding = PaddingValues(horizontal = 4.dp),
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                        ) {
                            items(items = uiState.vehicles, key = { it.id }) { vehicle ->
                                VehicleCarouselCard(
                                    state = vehicle,
                                    onClick = { component.onVehicleSelected(vehicle.id) },
                                )
                            }
                        }
                    }
                    item {
                        SeatsCard(
                            title = uiState.seatsTitle,
                            subtitle = uiState.seatsSubtitle,
                            value = uiState.seatsValue,
                            canDec = uiState.canDecrementSeats,
                            canInc = uiState.canIncrementSeats,
                            onDec = component::onDecrementSeats,
                            onInc = component::onIncrementSeats,
                        )
                    }
                    item {
                        PriceCard(
                            title = uiState.priceTitle,
                            value = uiState.priceInput,
                            currency = uiState.priceCurrency,
                            hint = uiState.priceHint,
                            placeholder = uiState.pricePlaceholder,
                            onValueChange = component::onPriceChange,
                        )
                    }
                    item {
                        ExtrasCard(
                            title = uiState.extrasTitle,
                            baggageLabel = uiState.baggageLabel,
                            parcelLabel = uiState.parcelLabel,
                            baggageAllowed = uiState.baggageAllowed,
                            parcelAllowed = uiState.parcelAllowed,
                            onBaggageToggle = component::onBaggageToggle,
                            onParcelToggle = component::onParcelToggle,
                        )
                    }
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(AppTheme.colors.background.default)
                .padding(horizontal = 16.dp, vertical = 12.dp),
        ) {
            PrimaryButton(
                uiState = uiState.continueButtonUiState,
                modifier = Modifier.fillMaxWidth(),
                onClick = component::onContinueClick,
            )
            bottomSpacer()
        }
    }
}

@Composable
private fun SeatsCard(
    title: UiText,
    subtitle: UiText,
    value: String,
    canDec: Boolean,
    canInc: Boolean,
    onDec: () -> Unit,
    onInc: () -> Unit,
) {
    ElevatedCard(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = AppTheme.colors.background.default,
        ),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(2.dp),
            ) {
                Text(
                    text = title.getString(),
                    style = AppTheme.typography.subtitle,
                    color = AppTheme.colors.text.default,
                )
                Text(
                    text = subtitle.getString(),
                    style = AppTheme.typography.caption,
                    color = AppTheme.colors.text.tertiary,
                )
            }
            CounterStepper(
                value = value,
                canDecrement = canDec,
                canIncrement = canInc,
                onDecrement = onDec,
                onIncrement = onInc,
            )
        }
    }
}

@Composable
private fun PriceCard(
    title: UiText,
    value: String,
    currency: UiText,
    hint: UiText,
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
                text = title.getString(),
                style = AppTheme.typography.subtitle,
                color = AppTheme.colors.text.default,
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                OutlinedTextField(
                    value = value,
                    onValueChange = onValueChange,
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(14.dp)),
                    placeholder = {
                        Text(
                            text = placeholder.getString(),
                            color = AppTheme.colors.text.tertiary,
                        )
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    shape = RoundedCornerShape(14.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = AppTheme.colors.border.secondary,
                        unfocusedBorderColor = AppTheme.colors.border.divider,
                    ),
                )
                Text(
                    text = currency.getString(),
                    style = AppTheme.typography.subtitle,
                    color = AppTheme.colors.text.tertiary,
                )
            }
            Text(
                text = hint.getString(),
                style = AppTheme.typography.caption,
                color = AppTheme.colors.text.tertiary,
            )
        }
    }
}

@Composable
private fun ExtrasCard(
    title: UiText,
    baggageLabel: UiText,
    parcelLabel: UiText,
    baggageAllowed: Boolean,
    parcelAllowed: Boolean,
    onBaggageToggle: (Boolean) -> Unit,
    onParcelToggle: (Boolean) -> Unit,
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
            Text(
                text = title.getString(),
                style = AppTheme.typography.subtitle,
                color = AppTheme.colors.text.default,
            )
            SettingToggleRow(
                iconRes = Res.drawable.ic_sdcard,
                title = baggageLabel.getString(),
                checked = baggageAllowed,
                onCheckedChange = onBaggageToggle,
            )
            SettingToggleRow(
                iconRes = Res.drawable.ic_folder,
                title = parcelLabel.getString(),
                checked = parcelAllowed,
                onCheckedChange = onParcelToggle,
            )
        }
    }
}

@Preview
@Composable
private fun CreateTripVehicleScreenPreview() {
    CreateTripVehicleScreen(component = PreviewCreateTripVehicleComponent())
}
