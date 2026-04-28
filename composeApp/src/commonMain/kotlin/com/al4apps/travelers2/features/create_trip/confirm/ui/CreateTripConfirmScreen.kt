package com.al4apps.travelers2.features.create_trip.confirm.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
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
import com.al4apps.travelers2.design_system.HomeTopBar
import com.al4apps.travelers2.design_system.RouteTimelineCard
import com.al4apps.travelers2.design_system.RouteTimelinePoint
import com.al4apps.travelers2.design_system.button.PrimaryButton
import com.al4apps.travelers2.features.create_trip.confirm.CreateTripConfirmComponent
import com.al4apps.travelers2.features.create_trip.confirm.PreviewCreateTripConfirmComponent
import com.al4apps.travelers2.theme.AppTheme
import com.arkivanov.decompose.extensions.compose.subscribeAsState

@Composable
fun CreateTripConfirmScreen(
    component: CreateTripConfirmComponent,
    bottomSpacer: @Composable () -> Unit = {},
) {
    val uiState by component.uiState.subscribeAsState()

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
            item { DriverCard(name = uiState.driverName, meta = uiState.driverMeta) }
            item {
                SectionCard(label = uiState.routeLabel) {
                    RouteTimelineCard(
                        departure = uiState.departure,
                        arrival = uiState.arrival,
                    )
                }
            }
            item {
                SectionCard(label = uiState.vehicleLabel) {
                    Text(
                        text = uiState.vehicleModel,
                        style = AppTheme.typography.subtitle,
                        color = AppTheme.colors.text.default,
                    )
                    Text(
                        text = uiState.vehiclePlate,
                        style = AppTheme.typography.body2,
                        color = AppTheme.colors.text.tertiary,
                    )
                }
            }
            item {
                SectionCard(label = uiState.conditionsLabel) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        InfoChip(text = uiState.seatsValue.getString(), modifier = Modifier.weight(1f))
                        InfoChip(text = uiState.priceValue.getString(), modifier = Modifier.weight(1f))
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        if (uiState.baggageEnabled) {
                            InfoChip(text = uiState.baggageLabel.getString(), modifier = Modifier.weight(1f))
                        }
                        if (uiState.parcelEnabled) {
                            InfoChip(text = uiState.parcelLabel.getString(), modifier = Modifier.weight(1f))
                        }
                    }
                }
            }
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(14.dp))
                        .background(AppTheme.colors.background.secondary)
                        .padding(12.dp),
                ) {
                    Text(
                        text = uiState.hint.getString(),
                        style = AppTheme.typography.body2,
                        color = AppTheme.colors.text.secondary,
                    )
                }
            }
            uiState.errorMessage?.let { error ->
                item {
                    Text(
                        text = error,
                        style = AppTheme.typography.body2,
                        color = AppTheme.colors.text.accent,
                    )
                }
            }
            item {
                PrimaryButton(
                    uiState = uiState.publishButtonUiState,
                    modifier = Modifier.fillMaxWidth(),
                    onClick = component::onPublishClick,
                )
            }
            item { bottomSpacer() }
        }
    }
}

@Composable
private fun DriverCard(name: String, meta: String) {
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
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(CircleShape)
                    .background(AppTheme.colors.background.gray),
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 12.dp),
                verticalArrangement = Arrangement.spacedBy(2.dp),
            ) {
                Text(
                    text = name,
                    style = AppTheme.typography.subtitle,
                    color = AppTheme.colors.text.default,
                )
                Text(
                    text = meta,
                    style = AppTheme.typography.caption,
                    color = AppTheme.colors.text.tertiary,
                )
            }
        }
    }
}

@Composable
private fun SectionCard(label: UiText, content: @Composable androidx.compose.foundation.layout.ColumnScope.() -> Unit) {
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
                text = label.getString().uppercase(),
                style = AppTheme.typography.caption,
                color = AppTheme.colors.text.tertiary,
            )
            content()
        }
    }
}

@Composable
private fun InfoChip(text: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(AppTheme.colors.background.gray)
            .padding(horizontal = 12.dp, vertical = 8.dp),
    ) {
        Text(
            text = text,
            style = AppTheme.typography.body2,
            color = AppTheme.colors.text.default,
        )
    }
}

@Preview
@Composable
private fun CreateTripConfirmScreenPreview() {
    CreateTripConfirmScreen(component = PreviewCreateTripConfirmComponent())
}
