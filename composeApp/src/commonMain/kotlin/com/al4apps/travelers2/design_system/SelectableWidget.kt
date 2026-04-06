package com.al4apps.travelers2.design_system

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.vectorResource
import travelers2.composeapp.generated.resources.Res
import travelers2.composeapp.generated.resources.checkmark
import travelers2.composeapp.generated.resources.star
import com.al4apps.travelers2.theme.AppTheme

@Composable
fun SelectableWidget(
    isSelected: Boolean,
    modifier: Modifier = Modifier,
    iconRes: DrawableResource? = null,
    onClicked: () -> Unit = {}
) {
    Card(
        modifier = modifier.size(36.dp),
        shape = CircleShape
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clickable(enabled = true) { onClicked() }
        ) {
            if (isSelected) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(AppTheme.colors.background.primary),
                    contentAlignment = Alignment.Center
                ) {
                    val icon = iconRes ?: Res.drawable.checkmark
                    Icon(
                        imageVector = vectorResource(icon),
                        contentDescription = null,
                        tint = AppTheme.colors.icon.white
                    )
                }
            }
        }
    }
}

@Composable
fun IconWidget(
    modifier: Modifier = Modifier,
    iconRes: DrawableResource,
    onClicked: () -> Unit = {}
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = vectorResource(iconRes),
            contentDescription = null,
            tint = AppTheme.colors.icon.success
        )
    }

}

@Composable
@Preview
fun SelectableWidgetPreview() {
//    SelectableWidget(true)
    IconWidget(iconRes = Res.drawable.star)
}
