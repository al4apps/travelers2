package com.al4apps.travelers2.features.home.profile.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.al4apps.travelers2.features.home.profile.PreviewProfileComponent
import com.al4apps.travelers2.features.home.profile.ProfileComponent

@Composable
fun ProfileScreen(
    component: ProfileComponent,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize(),
    )
}

@Preview
@Composable
private fun ProfileScreenPreview() {
    ProfileScreen(component = PreviewProfileComponent())
}
