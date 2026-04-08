package com.al4apps.travelers2.features.create_trip.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.al4apps.travelers2.features.create_trip.CreateTripComponent
import com.al4apps.travelers2.features.create_trip.PreviewCreateTripComponent

@Composable
fun CreateTripScreen(
    component: CreateTripComponent,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize(),
    )
}

@Preview
@Composable
private fun CreateTripScreenPreview() {
    CreateTripScreen(component = PreviewCreateTripComponent())
}
