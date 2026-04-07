package com.al4apps.travelers2.features.home.search_trips.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.al4apps.travelers2.features.home.search_trips.PreviewSearchTripsComponent
import com.al4apps.travelers2.features.home.search_trips.SearchTripsComponent

@Composable
fun SearchTripsScreen(
    component: SearchTripsComponent,
) {
    Box(
        modifier = Modifier.fillMaxSize(),
    )
}

@Preview
@Composable
private fun SearchTripsScreenPreview() {
    SearchTripsScreen(component = PreviewSearchTripsComponent())
}
