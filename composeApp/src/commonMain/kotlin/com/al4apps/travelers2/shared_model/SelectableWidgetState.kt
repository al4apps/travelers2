package com.al4apps.travelers2.shared_model

import org.jetbrains.compose.resources.DrawableResource
import travelers2.composeapp.generated.resources.Res
import travelers2.composeapp.generated.resources.checkmark

data class SelectableWidgetState(
    val icon: DrawableResource? = null,
    val isSelected: Boolean
) {
    companion object {
        val INITIAL = SelectableWidgetState(
            icon = Res.drawable.checkmark,
            isSelected = false
        )
    }
}
