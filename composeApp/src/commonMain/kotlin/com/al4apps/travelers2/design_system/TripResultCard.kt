package com.al4apps.travelers2.design_system

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.al4apps.travelers2.features.search_results.model.SearchResultTripUiModel
import com.al4apps.travelers2.theme.AppTheme
import org.jetbrains.compose.resources.painterResource
import travelers2.composeapp.generated.resources.Res
import travelers2.composeapp.generated.resources.arrow_right
import travelers2.composeapp.generated.resources.pin_blue
import travelers2.composeapp.generated.resources.pin_red

@Composable
fun TripResultCard(
    model: SearchResultTripUiModel,
    modifier: Modifier = Modifier,
) {
    ElevatedCard(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = AppTheme.colors.background.default,
        ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 14.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            HeaderRow(model = model)
            MainRow(model = model)
            HorizontalDivider(color = AppTheme.colors.border.divider)
            FooterRow(model = model)
        }
    }
}

@Composable
private fun HeaderRow(model: SearchResultTripUiModel) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            TimeText(model.departureTime)
            ArrowRight()
            TimeMetaText(model.durationText)
            ArrowRight()
            TimeText(model.arrivalTime)
        }
        Image(
            painter = painterResource(model.vehicleIconRes),
            contentDescription = null,
            modifier = Modifier.size(20.dp),
        )
    }
}

@Composable
private fun MainRow(model: SearchResultTripUiModel) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        Card(
            modifier = Modifier.size(108.dp),
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(containerColor = AppTheme.colors.background.gray),
        ) {
            Image(
                painter = painterResource(model.carImageRes),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(108.dp),
                contentScale = ContentScale.Crop,
            )
        }

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            AddressRow(
                iconRes = Res.drawable.pin_blue,
                title = model.fromAddress,
            )
            HorizontalDivider(
                color = AppTheme.colors.border.divider,
                modifier = Modifier.padding(start = 20.dp),
            )
            AddressRow(
                iconRes = Res.drawable.pin_red,
                title = model.toAddress,
            )
        }
    }
}

@Composable
private fun FooterRow(model: SearchResultTripUiModel) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Card(
                modifier = Modifier.size(32.dp),
                shape = RoundedCornerShape(10.dp),
                colors = CardDefaults.cardColors(containerColor = AppTheme.colors.background.gray),
            ) {
                Image(
                    painter = painterResource(model.driverImageRes),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(32.dp),
                    contentScale = ContentScale.Crop,
                )
            }
            Text(
                text = model.driverName,
                style = AppTheme.typography.body2,
                color = AppTheme.colors.text.default,
            )
        }

        Card(
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = AppTheme.colors.background.primary),
        ) {
            Text(
                text = model.priceText,
                style = AppTheme.typography.subtitle,
                color = AppTheme.colors.text.default,
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
            )
        }
    }
}

@Composable
private fun AddressRow(
    iconRes: org.jetbrains.compose.resources.DrawableResource,
    title: String,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        Image(
            painter = painterResource(iconRes),
            contentDescription = null,
            modifier = Modifier.size(16.dp),
        )
        Text(
            text = title,
            style = AppTheme.typography.subtitle,
            color = AppTheme.colors.text.default,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Composable
private fun ArrowRight() {
    Image(
        painter = painterResource(Res.drawable.arrow_right),
        contentDescription = null,
        modifier = Modifier.size(14.dp),
    )
}

@Composable
private fun TimeText(value: String) {
    Text(
        text = value,
        style = AppTheme.typography.body2,
        color = AppTheme.colors.text.default,
    )
}

@Composable
private fun TimeMetaText(value: String) {
    Text(
        text = value,
        style = AppTheme.typography.body2,
        color = AppTheme.colors.text.tertiary,
    )
}

@Preview
@Composable
private fun TripResultCardPreview() {
    Box(modifier = Modifier.padding(16.dp)) {
        TripResultCard(
            model = SearchResultTripUiModel(
                id = "preview",
                departureTime = "09:00",
                durationText = "3 ч",
                arrivalTime = "12:00",
                fromAddress = "Исфара",
                toAddress = "Худжанд",
                driverName = "Искандар",
                priceText = "200 с.",
            ),
        )
    }
}
