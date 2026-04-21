package com.al4apps.travelers2.features.trip_details.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.al4apps.travelers2.core.ui.getString
import com.al4apps.travelers2.design_system.HomeTopBar
import com.al4apps.travelers2.design_system.trip_details.TripDetailsBookingCard
import com.al4apps.travelers2.design_system.trip_details.TripDetailsDriverCard
import com.al4apps.travelers2.design_system.trip_details.TripDetailsOptionCard
import com.al4apps.travelers2.design_system.trip_details.TripDetailsRouteCard
import com.al4apps.travelers2.design_system.trip_details.TripDetailsSeatItemState
import com.al4apps.travelers2.design_system.trip_details.TripDetailsSeatsCard
import com.al4apps.travelers2.design_system.trip_details.TripDetailsVehicleCard
import com.al4apps.travelers2.features.trip_details.PreviewTripDetailsComponent
import com.al4apps.travelers2.features.trip_details.TripDetailsComponent
import com.al4apps.travelers2.features.trip_details.model.TripDetailsSeatUiModel
import com.al4apps.travelers2.features.trip_details.model.TripSeatType
import com.al4apps.travelers2.theme.AppTheme
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import travelers2.composeapp.generated.resources.Res
import travelers2.composeapp.generated.resources.car_seat
import travelers2.composeapp.generated.resources.ic_plus
import travelers2.composeapp.generated.resources.profile

@Composable
fun TripDetailsScreen(
    component: TripDetailsComponent,
    bottomSpacer: @Composable () -> Unit = {},
) {
    val uiState by component.uiState.subscribeAsState()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        HomeTopBar(
            state = uiState.topBarState,
            onBackClick = component::onBackClick,
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .safeDrawingPadding(),
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 6.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                item {
                    TripDetailsDriverCard(
                        avatarRes = uiState.driverAvatarRes,
                        driverName = uiState.driverName.getString(),
                        tripsText = uiState.driverTripsText.getString(),
                    )
                }
                item {
                    TripDetailsRouteCard(
                        departureTime = uiState.departureTime.getString(),
                        departureCity = uiState.departureCity.getString(),
                        departureDistrict = uiState.departureDistrict.getString(),
                        arrivalTime = uiState.arrivalTime.getString(),
                        arrivalCity = uiState.arrivalCity.getString(),
                        arrivalDistrict = uiState.arrivalDistrict.getString(),
                    )
                }
                item {
                    TripDetailsVehicleCard(
                        model = uiState.carModel.getString(),
                        plateNumber = uiState.carPlateNumber.getString(),
                        imageRes = uiState.carImageRes,
                    )
                }
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                    ) {
                        uiState.options.forEach { option ->
                            TripDetailsOptionCard(
                                title = option.title.getString(),
                                iconRes = option.iconRes,
                                isAvailable = option.isAvailable,
                                modifier = Modifier.weight(1f),
                            )
                        }
                    }
                }
                item {
                    TripDetailsSeatsCard(
                        title = uiState.seatsTitle.getString(),
                        seats = uiState.seats.map { it.toSeatItemState() },
                        modifier = Modifier.align(Alignment.BottomCenter)
                    )
                }
                item { bottomSpacer() }
            }
            TripDetailsBookingCard(
                modifier = Modifier.align(Alignment.BottomCenter).padding(horizontal = 16.dp),
                priceTitle = uiState.priceTitle.getString(),
                priceValue = uiState.priceValue.getString(),
                buttonTitle = uiState.bookingButtonTitle.getString(),
                onBookClick = component::onBookClick,
            )
        }
    }
}

@Composable
private fun TripDetailsSeatUiModel.toSeatItemState(): TripDetailsSeatItemState =
    when (type) {
        TripSeatType.DRIVER -> TripDetailsSeatItemState(
            iconRes = Res.drawable.car_seat,
            iconTint = AppTheme.colors.icon.grayDark,
            backgroundColor = AppTheme.colors.background.gray,
            showDashedLikeBorder = false,
        )

        TripSeatType.OCCUPIED -> TripDetailsSeatItemState(
            iconRes = Res.drawable.profile,
            iconTint = AppTheme.colors.icon.accent,
            backgroundColor = AppTheme.colors.background.secondary,
            showDashedLikeBorder = false,
        )

        TripSeatType.AVAILABLE -> TripDetailsSeatItemState(
            iconRes = Res.drawable.ic_plus,
            iconTint = AppTheme.colors.icon.accent,
            backgroundColor = AppTheme.colors.background.default,
            showDashedLikeBorder = true,
        )
    }

@Preview
@Composable
private fun TripDetailsScreenPreview() {
    TripDetailsScreen(component = PreviewTripDetailsComponent())
}
