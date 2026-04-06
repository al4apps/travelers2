package com.al4apps.travelers2.shared_model

import org.jetbrains.compose.resources.DrawableResource

data class DockUiState(
    val items: List<DockButtonUiState>
)

sealed interface DockButtonUiState {
    val id: Long
    val title: String
    val isSelected: Boolean
    val isEnabled: Boolean
}

data class DockIconTextButtonUiState(
    override val id: Long,
    override val title: String,
    val icon: DrawableResource?,
    override val isSelected: Boolean = false,
    override val isEnabled: Boolean = true
) : DockButtonUiState

data class DockTextButtonUiState(
    override val id: Long,
    override val title: String,
    override val isSelected: Boolean = false,
    override val isEnabled: Boolean = true,
    val isDestructive: Boolean = false,
) : DockButtonUiState
