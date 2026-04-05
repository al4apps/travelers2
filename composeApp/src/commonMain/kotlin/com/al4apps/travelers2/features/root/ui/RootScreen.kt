package com.al4apps.travelers2.features.root.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.al4apps.travelers2.features.home.ui.HomeScreen
import com.al4apps.travelers2.features.onboarding.ui.OnboardingScreen
import com.al4apps.travelers2.features.root.PreviewRootComponent
import com.al4apps.travelers2.features.root.RootChild
import com.al4apps.travelers2.features.root.RootComponent
import com.arkivanov.decompose.extensions.compose.stack.Children

@Composable
fun RootScreen(
    component: RootComponent,
    modifier: Modifier = Modifier,
) {
    Children(
        stack = component.stack,
    ) { child ->
        when (val instance = child.instance) {
            is RootChild.Home -> HomeScreen(
                component = instance.component,
                modifier = modifier,
            )

            is RootChild.Onboarding -> OnboardingScreen(
                component = instance.component,
                modifier = modifier,
            )
        }
    }
}

@Preview
@Composable
private fun RootScreenPreview() {
    RootScreen(component = PreviewRootComponent())
}
