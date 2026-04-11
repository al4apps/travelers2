package com.al4apps.travelers2.features.search_trips.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.al4apps.travelers2.core.ui.UiText
import com.al4apps.travelers2.core.ui.asString
import com.al4apps.travelers2.design_system.button.PrimaryButton
import com.al4apps.travelers2.design_system.input.TravelersFormTextField
import com.al4apps.travelers2.features.search_trips.PreviewSearchTripsComponent
import com.al4apps.travelers2.features.search_trips.SearchTripsChild
import com.al4apps.travelers2.features.search_trips.SearchTripsComponent
import com.al4apps.travelers2.features.search_trips.address_search.ui.AddressSearchScreen
import com.al4apps.travelers2.features.search_trips.calendar.ui.CalendarContent
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
            SearchTripsChild.Form -> SearchTripsCardContent {
                SearchTripsFormContent(
                    title = uiState.title.asString(),
                    fromAddress = uiState.fromAddress,
                    toAddress = uiState.toAddress,
                    tripDate = uiState.tripDate,
                    fromPlaceholder = uiState.fromPlaceholder,
                    toPlaceholder = uiState.toPlaceholder,
                    datePlaceholder = uiState.datePlaceholder,
                    buttonText = uiState.searchButtonText.asString(),
                    onFromClick = component::onFromClick,
                    onToClick = component::onToClick,
                    onDateClick = component::onDateClick,
                    onSearchClick = component::onSearchClick,
                )
            }

            is SearchTripsChild.Calendar -> SearchTripsCardContent {
                CalendarContent(component = instance.component)
            }

            is SearchTripsChild.AddressSearch -> AddressSearchScreen(
                component = instance.component,
                modifier = modifier,
            )
        }
    }
}

@Composable
private fun SearchTripsCardContent(
    content: @Composable ColumnScope.() -> Unit,
) {
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
                content = content,
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
    fromPlaceholder: UiText,
    toPlaceholder: UiText,
    datePlaceholder: UiText,
    buttonText: String,
    onFromClick: () -> Unit,
    onToClick: () -> Unit,
    onDateClick: () -> Unit,
    onSearchClick: () -> Unit,
) {
    Text(
        text = title,
        style = AppTheme.typography.h2,
        color = AppTheme.colors.text.default,
    )
    Spacer(modifier = Modifier.height(2.dp))
    TravelersFormTextField(
        value = fromAddress,
        placeholder = fromPlaceholder,
        icon = Res.drawable.pin,
        enabled = false,
        readOnly = true,
        onClick = onFromClick,
        onValueChange = {},
    )
    TravelersFormTextField(
        value = toAddress,
        placeholder = toPlaceholder,
        icon = Res.drawable.pin,
        readOnly = true,
        enabled = false,
        onClick = onToClick,
        onValueChange = {},
    )
    TravelersFormTextField(
        value = tripDate,
        placeholder = datePlaceholder,
        icon = Res.drawable.calendar,
        enabled = false,
        readOnly = true,
        onClick = onDateClick,
        onValueChange = {},
    )
    Spacer(modifier = Modifier.height(8.dp))
    PrimaryButton(
        text = buttonText,
        enabled = true,
        modifier = Modifier.widthIn(min = 160.dp),
        onClick = onSearchClick,
    )
}

@Preview
@Composable
private fun SearchTripsScreenPreview() {
    SearchTripsScreen(component = PreviewSearchTripsComponent())
}
