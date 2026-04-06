package com.al4apps.travelers2

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.al4apps.travelers2.features.root.RootComponent
import com.al4apps.travelers2.features.root.ui.RootScreen
import com.al4apps.travelers2.theme.AppTheme

@Composable
fun App(
    rootComponent: RootComponent,
    modifier: Modifier = Modifier,
) {
    AppTheme {
        RootScreen(
            component = rootComponent,
            modifier = modifier,
        )
    }
}
