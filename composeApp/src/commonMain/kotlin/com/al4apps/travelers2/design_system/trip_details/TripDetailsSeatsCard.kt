package com.al4apps.travelers2.design_system.trip_details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import com.al4apps.travelers2.theme.AppTheme
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun TripDetailsSeatsCard(
    title: String,
    seats: List<TripDetailsSeatItemState>,
    modifier: Modifier = Modifier,
) {
    TripDetailsSectionCard(modifier = modifier) {
        Text(
            text = title,
            style = AppTheme.typography.body1,
            color = AppTheme.colors.text.default,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            seats.forEach { seat ->
                SeatIcon(
                    state = seat,
                    modifier = Modifier.weight(1f),
                )
            }
        }
    }
}

@Composable
private fun SeatIcon(
    state: TripDetailsSeatItemState,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        Box(
            modifier = Modifier
                .size(52.dp)
                .background(state.backgroundColor, CircleShape)
                .border(
                    width = if (state.showDashedLikeBorder) 1.dp else 0.dp,
                    color = if (state.showDashedLikeBorder) AppTheme.colors.border.secondaryDisabled else Color.Transparent,
                    shape = CircleShape,
                ),
            contentAlignment = Alignment.Center,
        ) {
            Image(
                painter = painterResource(state.iconRes),
                contentDescription = null,
                modifier = Modifier.size(18.dp),
                colorFilter = ColorFilter.tint(state.iconTint),
            )
        }
    }
}

data class TripDetailsSeatItemState(
    val iconRes: DrawableResource,
    val iconTint: Color,
    val backgroundColor: Color,
    val showDashedLikeBorder: Boolean,
)
