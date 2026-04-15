package com.al4apps.travelers2.features.home.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.al4apps.travelers2.core.ui.getString
import com.al4apps.travelers2.design_system.dock.DockBar
import com.al4apps.travelers2.features.create_trip.ui.CreateTripScreen
import com.al4apps.travelers2.features.home.HomeChild
import com.al4apps.travelers2.features.home.HomeComponent
import com.al4apps.travelers2.features.home.PreviewHomeComponent
import com.al4apps.travelers2.features.profile.ui.ProfileScreen
import com.al4apps.travelers2.features.search.ui.SearchScreen
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.subscribeAsState

private val dockBarHeight = 56.dp

@Composable
fun HomeScreen(
    component: HomeComponent,
    modifier: Modifier = Modifier,
) {
    val uiState by component.uiState.subscribeAsState()
    val stack by component.stack.subscribeAsState()
    val errorMessage = uiState.errorMessage
    val bottomSpace = dockBarHeight + 8.dp

    when {
        uiState.isLoading -> Box(
            modifier = modifier
                .fillMaxSize()
                .safeDrawingPadding()
                .padding(horizontal = 24.dp, vertical = 32.dp),
            contentAlignment = Alignment.Center,
        ) {
            CircularProgressIndicator()
        }

        errorMessage != null -> Box(
            modifier = modifier
                .fillMaxSize()
                .safeDrawingPadding()
                .padding(horizontal = 24.dp, vertical = 32.dp),
            contentAlignment = Alignment.Center,
        ) {
            ScreenContent(
                title = "Home",
                message = errorMessage.getString(),
                buttonLabel = "Retry",
                onButtonClick = component::onRetryClick,
            )
        }

        else -> Column(
            modifier = modifier
                .fillMaxSize()
                .safeDrawingPadding()
                .padding(vertical = 16.dp),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .navigationBarsPadding(),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                ) {
                    Children(stack = stack) { child ->
                        when (val instance = child.instance) {
                            is HomeChild.SearchTrips -> SearchScreen(
                                component = instance.component,
                                bottomSpacer = { Spacer(modifier = Modifier.height(bottomSpace)) }
                            )

                            is HomeChild.CreateTrip -> CreateTripScreen(component = instance.component)
                            is HomeChild.Profile -> ProfileScreen(component = instance.component)
                        }
                    }
                }
                DockBar(
                    items = component.getDockItems(),
                    modifier = Modifier
                        .height(dockBarHeight)
                        .align(Alignment.BottomCenter),
                    onItemClick = component::onDockItemClick,
                )
            }
        }
    }
}

@Composable
private fun ScreenContent(
    title: String,
    message: String,
    buttonLabel: String? = null,
    onButtonClick: (() -> Unit)? = null,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center,
        )
        Text(
            text = message,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
        )

        if (buttonLabel != null && onButtonClick != null) {
            Button(onClick = onButtonClick) {
                Text(buttonLabel)
            }
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen(component = PreviewHomeComponent())
}
