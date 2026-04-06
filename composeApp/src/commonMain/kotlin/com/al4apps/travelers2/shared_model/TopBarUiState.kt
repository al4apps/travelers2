package com.al4apps.travelers2.shared_model

import org.jetbrains.compose.resources.DrawableResource

data class TopBarUiState(
    val title: String,
    val navIcon: DrawableResource?,
    val selectableWidgetState: SelectableWidgetState?,
    val actionIcon: DrawableResource?,
    val isActionClickable: Boolean,
    val isTitleEditable: Boolean,
    val isTitleEditing: Boolean,
) {

    companion object {
        val INITIAL = TopBarUiState(
            title = "ListApp",
            navIcon = null,
            selectableWidgetState = null,
            actionIcon = null,
            isActionClickable = false,
            isTitleEditable = false,
            isTitleEditing = false,
        )
    }
}
