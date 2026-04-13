package com.al4apps.travelers2.features.search_results.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.al4apps.travelers2.features.search_results.PreviewSearchResultsComponent
import com.al4apps.travelers2.features.search_results.SearchResultsComponent
import com.al4apps.travelers2.theme.AppTheme

@Composable
fun SearchResultsScreen(
    component: SearchResultsComponent,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Button(onClick = component::onBackClick) {
            Text("Назад")
        }
        Text(
            text = "Результаты поиска (placeholder)",
            style = AppTheme.typography.h2,
            color = AppTheme.colors.text.default,
        )
        Text(
            text = "Откуда: ${component.query.fromAddress}",
            style = AppTheme.typography.body1,
            color = AppTheme.colors.text.default,
            modifier = Modifier.fillMaxWidth(),
        )
        Text(
            text = "Куда: ${component.query.toAddress}",
            style = AppTheme.typography.body1,
            color = AppTheme.colors.text.default,
            modifier = Modifier.fillMaxWidth(),
        )
        Text(
            text = "Когда: ${component.query.tripDate}",
            style = AppTheme.typography.body1,
            color = AppTheme.colors.text.default,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Preview
@Composable
private fun SearchResultsScreenPreview() {
    SearchResultsScreen(component = PreviewSearchResultsComponent())
}
