package com.al4apps.travelers2.features.search_trips.ui

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.al4apps.travelers2.core.ui.asString
import com.al4apps.travelers2.design_system.button.PrimaryButton
import com.al4apps.travelers2.design_system.input.TravelersFormTextField
import com.al4apps.travelers2.features.search_trips.PreviewSearchTripsComponent
import com.al4apps.travelers2.features.search_trips.SearchTripsChild
import com.al4apps.travelers2.features.search_trips.SearchTripsComponent
import com.al4apps.travelers2.features.search_trips.address_search.ui.AddressSearchScreen
import com.al4apps.travelers2.theme.AppTheme
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import travelers2.composeapp.generated.resources.Res
import travelers2.composeapp.generated.resources.calendar
import travelers2.composeapp.generated.resources.pin

@Composable
fun SearchTripsScreen(
    component: SearchTripsComponent,
    modifier: Modifier = Modifier,
) {
    val uiState by component.uiState.subscribeAsState()

    Children(
        stack = component.stack,
        modifier = modifier,
    ) { child ->
        when (val instance = child.instance) {
            SearchTripsChild.Form -> SearchTripsFormContent(
                title = uiState.title.asString(),
                fromAddress = uiState.fromAddress,
                toAddress = uiState.toAddress,
                tripDate = uiState.tripDate,
                fromPlaceholder = uiState.fromPlaceholder.asString(),
                toPlaceholder = uiState.toPlaceholder.asString(),
                datePlaceholder = uiState.datePlaceholder.asString(),
                buttonText = uiState.searchButtonText.asString(),
                onFromClick = component::onFromClick,
                onToClick = component::onToClick,
                onDateChange = component::onDateChange,
                onSearchClick = component::onSearchClick,
            )

            is SearchTripsChild.AddressSearch -> AddressSearchScreen(
                component = instance.component,
                modifier = modifier,
            )
        }
    }
}

@Composable
private fun SearchTripsFormContent(
    title: String,
    fromAddress: String,
    toAddress: String,
    tripDate: String,
    fromPlaceholder: String,
    toPlaceholder: String,
    datePlaceholder: String,
    buttonText: String,
    onFromClick: () -> Unit,
    onToClick: () -> Unit,
    onDateChange: (String) -> Unit,
    onSearchClick: () -> Unit,
) {
    val source = remember { MutableInteractionSource() }
    if (source.collectIsPressedAsState().value) onFromClick()
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
                    text = title,
                    style = AppTheme.typography.h2,
                    color = AppTheme.colors.text.default,
                )
                Spacer(modifier = Modifier.height(2.dp))
                TravelersFormTextField(
                    value = fromAddress,
                    placeholder = com.al4apps.travelers2.core.ui.UiText.Plain(fromPlaceholder),
                    icon = Res.drawable.pin,
                    enabled = false,
                    readOnly = true,
                    onClick = onFromClick,
                    onValueChange = {},
                )
                TravelersFormTextField(
                    value = toAddress,
                    placeholder = com.al4apps.travelers2.core.ui.UiText.Plain(toPlaceholder),
                    icon = Res.drawable.pin,
                    readOnly = true,
                    enabled = false,
                    onClick = onToClick,
                    onValueChange = {},
                )
                TravelersFormTextField(
                    value = tripDate,
                    placeholder = com.al4apps.travelers2.core.ui.UiText.Plain(datePlaceholder),
                    icon = Res.drawable.calendar,
                    onValueChange = onDateChange,
                )
                Spacer(modifier = Modifier.height(8.dp))
                PrimaryButton(
                    text = buttonText,
                    enabled = true,
                    modifier = Modifier.widthIn(min = 160.dp),
                    onClick = onSearchClick,
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
