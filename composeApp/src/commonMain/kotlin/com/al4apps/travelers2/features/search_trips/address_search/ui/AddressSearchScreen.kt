package com.al4apps.travelers2.features.search_trips.address_search.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.al4apps.travelers2.core.model.TripAddress
import com.al4apps.travelers2.core.model.toDisplayString
import com.al4apps.travelers2.design_system.input.TravelersFormTextField
import com.al4apps.travelers2.features.search_trips.address_search.AddressSearchComponent
import com.al4apps.travelers2.features.search_trips.address_search.PreviewAddressSearchComponent
import com.al4apps.travelers2.theme.AppTheme
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import travelers2.composeapp.generated.resources.Res
import travelers2.composeapp.generated.resources.ic_arrow_back

@Composable
fun AddressSearchScreen(
    component: AddressSearchComponent,
    modifier: Modifier = Modifier,
) {
    val uiState by component.uiState.subscribeAsState()

    Column(
        modifier = modifier.fillMaxSize(),
    ) {
        TravelersFormTextField(
            value = uiState.query,
            placeholder = uiState.placeholder,
            icon = Res.drawable.ic_arrow_back,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            onValueChange = component::onQueryChange,
            onIconClick = component::onBackClick
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 8.dp),
        ) {
            items(
                items = uiState.results,
                key = { address -> "${address.country}_${address.region}_${address.town}_${address.locality.orEmpty()}" },
            ) { address ->
                AddressResultItem(
                    address = address,
                    onClick = { component.onAddressClick(address) },
                )
            }
        }
    }
}

@Composable
private fun AddressResultItem(
    address: TripAddress,
    onClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp),
    ) {
        Text(
            text = address.toDisplayString(),
            style = AppTheme.typography.body1,
            color = AppTheme.colors.text.default,
            modifier = Modifier.padding(vertical = 16.dp),
        )
        HorizontalDivider(color = AppTheme.colors.border.divider)
    }
}

@Composable
@androidx.compose.ui.tooling.preview.Preview
private fun AddressSearchScreenPreview() {
    AddressSearchScreen(component = PreviewAddressSearchComponent())
}
