package com.al4apps.travelers2.shared_model

import com.al4apps.travelers2.core.ui.UiText
import org.jetbrains.compose.resources.DrawableResource
import travelers2.composeapp.generated.resources.Res
import travelers2.composeapp.generated.resources.ic_arrow_back

data class HomeTopBarUiState(
    val title: UiText = UiText.Plain(""),
    val isBackVisible: Boolean = false,
    val backIcon: DrawableResource = Res.drawable.ic_arrow_back,
    val isBackClickable: Boolean = true,
    val leadingIcon: DrawableResource? = null,
    val isLeadingClickable: Boolean = true,
    val actionIconPrimary: DrawableResource? = null,
    val isActionPrimaryClickable: Boolean = true,
    val actionIconSecondary: DrawableResource? = null,
    val isActionSecondaryClickable: Boolean = true,
) {
    companion object {
        val EMPTY = HomeTopBarUiState()
    }
}
