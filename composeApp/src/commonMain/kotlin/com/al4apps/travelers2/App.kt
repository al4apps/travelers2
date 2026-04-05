package com.al4apps.travelers2

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.al4apps.travelers2.features.root.RootComponent
import com.al4apps.travelers2.features.root.ui.RootScreen

@Composable
fun App(
    rootComponent: RootComponent,
    modifier: Modifier = Modifier,
) {
    MaterialTheme {
        RootScreen(
            component = rootComponent,
            modifier = modifier,
        )
    }
}
