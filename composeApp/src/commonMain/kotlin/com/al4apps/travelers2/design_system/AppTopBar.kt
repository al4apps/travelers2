package com.al4apps.travelers2.design_system

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.al4apps.kmp_wizard.design_system.SelectableWidget
import ru.intabia.platform.shared_model.SelectableWidgetState
import com.al4apps.kmp_wizard.shared_models.TopBarUiState
import intabiaplatform.composeapp.generated.resources.Res
import intabiaplatform.composeapp.generated.resources.ic_arrow_back
import org.jetbrains.compose.resources.painterResource
import ru.intabia.platform.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    state: TopBarUiState,
    onBackClick: () -> Unit,
    onActionClick: () -> Unit = {},
    onTitleClicked: () -> Unit = {},
    onTitleChanged: (String) -> Unit = {}
) {
    val titleTextStyle = AppTheme.typography.h2
    TopAppBar(
        title = {
            if (state.isTitleEditable) {
                BasicTextField(
                    value = state.title,
                    onValueChange = onTitleChanged,
                    singleLine = true,
                    textStyle = TextStyle(
                        color = AppTheme.colors.text.primary,
                        fontSize = titleTextStyle.fontSize,
                        fontWeight = titleTextStyle.fontWeight,
                        fontFamily = titleTextStyle.fontFamily,
                        letterSpacing = titleTextStyle.letterSpacing
                    )
                )
            } else {
                Text(
                    state.title,
                    style = titleTextStyle,
                    color = AppTheme.colors.text.primary,
                    modifier = if (state.isTitleEditable) {
                        Modifier.clickable(onClick = onTitleClicked)
                    } else {
                        Modifier
                    }
                )
            }
        },
        navigationIcon = {
            state.navIcon?.let {
                Card(
                    modifier = Modifier.size(36.dp),
                    shape = CircleShape,
                    colors = CardDefaults.cardColors().copy(
                        containerColor = Color.Transparent
                    ),
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable(
                                enabled = true,
                                onClick = { onBackClick() }
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(state.navIcon),
                            contentDescription = null,
                            tint = AppTheme.colors.icon.black,
                            modifier = Modifier.size(24.dp)
                        )

                    }
                }
            }
        },
        actions = {
            Box(
                modifier = Modifier.padding(end = 16.dp).clickable(
                    interactionSource = null,
                    enabled = state.isActionClickable,
                    onClick = onActionClick,
                    indication = null
                )
            ) {
                state.actionIcon?.let {
                    Icon(
                        painter = painterResource(state.actionIcon),
                        contentDescription = null,
                        modifier = Modifier
                            .size(24.dp)
                            .clickable(
                                enabled = true,
                                onClick = { onActionClick() }
                            )
                    )
                }
                state.selectableWidgetState?.let {
                    SelectableWidget(
                        isSelected = it.isSelected,
                        iconRes = it.icon
                    )
                }
            }
        }
    )
}

@Composable
@Preview
fun AppTopBarPreview() {
    AppTopBar(
        TopBarUiState.INITIAL.copy(
            navIcon = Res.drawable.ic_arrow_back,
            selectableWidgetState = SelectableWidgetState.INITIAL
        ),
        onBackClick = {})
}
