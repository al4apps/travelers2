package com.al4apps.travelers2.design_system.trip_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.al4apps.travelers2.theme.AppTheme

@Composable
fun TripDetailsRouteCard(
    departureTime: String,
    departureCity: String,
    departureDistrict: String,
    arrivalTime: String,
    arrivalCity: String,
    arrivalDistrict: String,
    modifier: Modifier = Modifier,
) {
    TripDetailsSectionCard(modifier = modifier) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            TimelineColumn()
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(14.dp),
            ) {
                TripStop(
                    time = departureTime,
                    city = departureCity,
                    district = departureDistrict,
                )
                TripStop(
                    time = arrivalTime,
                    city = arrivalCity,
                    district = arrivalDistrict,
                )
            }
        }
    }
}

@Composable
private fun TimelineColumn() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .size(14.dp)
                .clip(CircleShape)
                .background(AppTheme.colors.background.default),
            contentAlignment = Alignment.Center,
        ) {
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .clip(CircleShape)
                    .background(AppTheme.colors.button.primaryPush),
            )
        }
        Box(
            modifier = Modifier
                .size(width = 2.dp, height = 58.dp)
                .background(AppTheme.colors.background.gray),
        )
        Box(
            modifier = Modifier
                .size(14.dp)
                .clip(CircleShape)
                .background(AppTheme.colors.button.primary),
        )
    }
}

@Composable
private fun TripStop(
    time: String,
    city: String,
    district: String,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        Text(
            text = time,
            style = AppTheme.typography.body1,
            color = AppTheme.colors.text.primary,
        )
        Text(
            text = city,
            style = AppTheme.typography.subtitle,
            color = AppTheme.colors.text.default,
        )
        Text(
            text = district,
            style = AppTheme.typography.body2,
            color = AppTheme.colors.text.tertiary,
        )
    }
}
