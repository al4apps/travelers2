package com.al4apps.travelers2.features.search_trips.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.al4apps.travelers2.core.ui.UiText
import com.al4apps.travelers2.design_system.button.PrimaryButton
import com.al4apps.travelers2.design_system.input.TravelersFormTextField
import com.al4apps.travelers2.features.search_trips.PreviewSearchTripsComponent
import com.al4apps.travelers2.features.search_trips.SearchTripsComponent
import com.al4apps.travelers2.theme.AppTheme
import org.jetbrains.compose.resources.stringResource
import travelers2.composeapp.generated.resources.Res
import travelers2.composeapp.generated.resources.calendar
import travelers2.composeapp.generated.resources.pin
import travelers2.composeapp.generated.resources.search_trips_button
import travelers2.composeapp.generated.resources.search_trips_from
import travelers2.composeapp.generated.resources.search_trips_title
import travelers2.composeapp.generated.resources.search_trips_to
import travelers2.composeapp.generated.resources.search_trips_when

@Composable
fun SearchTripsScreen(component: SearchTripsComponent) {
    var departure by remember { mutableStateOf("") } // перенести в дальнейшем в компонент
    var destination by remember { mutableStateOf("") } // перенести в дальнейшем в компонент
    var tripDate by remember { mutableStateOf("") } // перенести в дальнейшем в компонент

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.Center,
    ) {
        ElevatedCard(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(
                containerColor = AppTheme.colors.background.default,
            ),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(14.dp),
            ) {
                Text(
                    text = stringResource(Res.string.search_trips_title),
                    style = AppTheme.typography.h2,
                    color = AppTheme.colors.text.default,
                )
                Spacer(modifier = Modifier.height(2.dp))
                TravelersFormTextField(
                    value = departure,
                    placeholder = UiText.Resource(Res.string.search_trips_from),
                    icon = Res.drawable.pin,
                    onValueChange = { departure = it },
                )
                TravelersFormTextField(
                    value = destination,
                    placeholder = UiText.Resource(Res.string.search_trips_to),
                    icon = Res.drawable.pin,
                    onValueChange = { destination = it },
                )
                TravelersFormTextField(
                    value = tripDate,
                    placeholder = UiText.Resource(Res.string.search_trips_when),
                    icon = Res.drawable.calendar,
                    onValueChange = { tripDate = it },
                )
                Spacer(modifier = Modifier.height(8.dp))
                PrimaryButton(
                    text = stringResource(Res.string.search_trips_button),
                    enabled = true,
                    modifier = Modifier.widthIn(min = 160.dp),
                    onClick = {},
                )
            }
        }
    }
}

@Preview
@Composable
private fun SearchTripsScreenPreview() {
    SearchTripsScreen(component = PreviewSearchTripsComponent())
}
