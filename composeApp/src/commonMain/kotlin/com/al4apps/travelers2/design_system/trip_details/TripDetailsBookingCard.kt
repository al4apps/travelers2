package com.al4apps.travelers2.design_system.trip_details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.al4apps.travelers2.theme.AppTheme

@Composable
fun TripDetailsBookingCard(
    priceTitle: String,
    priceValue: String,
    buttonTitle: String,
    onBookClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    ElevatedCard(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = AppTheme.colors.background.default),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                Text(
                    text = priceTitle,
                    style = AppTheme.typography.caption,
                    color = AppTheme.colors.text.default,
                )
                Text(
                    text = priceValue,
                    style = AppTheme.typography.h2,
                    color = AppTheme.colors.text.default,
                )
            }
            Button(
                onClick = onBookClick,
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = AppTheme.colors.button.primary,
                    contentColor = AppTheme.colors.button.primaryTextColor,
                ),
                modifier = Modifier
                    .weight(1.8f)
                    .height(48.dp),
            ) {
                Text(
                    text = buttonTitle,
                    style = AppTheme.typography.body1,
                )
            }
        }
    }
}
