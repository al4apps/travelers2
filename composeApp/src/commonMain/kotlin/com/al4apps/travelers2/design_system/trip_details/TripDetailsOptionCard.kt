package com.al4apps.travelers2.design_system.trip_details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import com.al4apps.travelers2.theme.AppTheme
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun TripDetailsOptionCard(
    title: String,
    iconRes: DrawableResource,
    isAvailable: Boolean,
    modifier: Modifier = Modifier,
) {
    ElevatedCard(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = AppTheme.colors.background.default),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            val iconTint = if (isAvailable) AppTheme.colors.icon.accent else AppTheme.colors.icon.grayDark
            val textColor = if (isAvailable) AppTheme.colors.text.default else AppTheme.colors.text.tertiary

            Image(
                painter = painterResource(iconRes),
                contentDescription = null,
                modifier = Modifier.height(18.dp),
                colorFilter = ColorFilter.tint(iconTint),
            )
            Text(
                text = title,
                style = AppTheme.typography.caption,
                color = textColor,
            )
        }
    }
}
