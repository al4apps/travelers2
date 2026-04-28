package com.al4apps.travelers2.design_system

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import com.al4apps.travelers2.theme.AppTheme
import org.jetbrains.compose.resources.painterResource
import travelers2.composeapp.generated.resources.Res
import travelers2.composeapp.generated.resources.car_white
import travelers2.composeapp.generated.resources.ic_check

data class VehicleCarouselItemState(
    val id: String,
    val model: String,
    val plateNumber: String,
    val seatsLabel: String,
    val isSelected: Boolean,
)

@Composable
fun VehicleCarouselCard(
    state: VehicleCarouselItemState,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val borderColor = if (state.isSelected) {
        AppTheme.colors.border.secondary
    } else {
        AppTheme.colors.border.divider
    }
    Card(
        modifier = modifier
            .width(220.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(width = if (state.isSelected) 2.dp else 1.dp, color = borderColor),
        colors = CardDefaults.cardColors(
            containerColor = AppTheme.colors.background.default,
        ),
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(14.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(96.dp)
                        .clip(RoundedCornerShape(14.dp))
                        .background(AppTheme.colors.background.gray),
                    contentAlignment = Alignment.Center,
                ) {
                    androidx.compose.foundation.Image(
                        painter = painterResource(Res.drawable.car_white),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(AppTheme.colors.icon.grayDark),
                        modifier = Modifier.size(72.dp),
                    )
                }
                Text(
                    text = state.model,
                    style = AppTheme.typography.subtitle,
                    color = AppTheme.colors.text.default,
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = state.plateNumber,
                        style = AppTheme.typography.body2,
                        color = AppTheme.colors.text.tertiary,
                    )
                    Text(
                        text = state.seatsLabel,
                        style = AppTheme.typography.body2,
                        color = AppTheme.colors.text.tertiary,
                    )
                }
            }
            if (state.isSelected) {
                Box(
                    modifier = Modifier
                        .padding(10.dp)
                        .size(24.dp)
                        .clip(CircleShape)
                        .background(AppTheme.colors.button.primary)
                        .align(Alignment.TopEnd),
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_check),
                        contentDescription = null,
                        tint = AppTheme.colors.icon.white,
                        modifier = Modifier.size(14.dp),
                    )
                }
            }
        }
    }
}
