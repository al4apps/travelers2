package com.al4apps.travelers2.features.search.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.al4apps.travelers2.features.search.PreviewSearchFeatureComponent
import com.al4apps.travelers2.features.search.SearchChild
import com.al4apps.travelers2.features.search.SearchFeatureComponent
import com.al4apps.travelers2.features.search_form.ui.SearchTripsScreen
import com.al4apps.travelers2.features.search_results.ui.SearchResultsScreen
import com.arkivanov.decompose.extensions.compose.stack.Children

@Composable
fun SearchScreen(
    component: SearchFeatureComponent,
    bottomSpacer: @Composable () -> Unit = {},
) {
    Children(
        stack = component.stack,
        modifier = Modifier,
    ) { child ->
        when (val instance = child.instance) {
            is SearchChild.Form -> SearchTripsScreen(component = instance.component)
            is SearchChild.Results -> SearchResultsScreen(
                component = instance.component,
                bottomSpacer
            )
        }
    }
}

@Preview
@Composable
private fun SearchScreenPreview() {
    SearchScreen(component = PreviewSearchFeatureComponent())
}
