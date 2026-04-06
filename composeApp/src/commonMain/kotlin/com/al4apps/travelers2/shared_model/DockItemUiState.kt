package com.al4apps.travelers2.shared_model

import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import travelers2.composeapp.generated.resources.Res
import travelers2.composeapp.generated.resources.dock_chats
import travelers2.composeapp.generated.resources.dock_contacts
import travelers2.composeapp.generated.resources.dock_settings
import travelers2.composeapp.generated.resources.exit
import travelers2.composeapp.generated.resources.profile
import travelers2.composeapp.generated.resources.search

data class DockItemUiState(
    val item: DockItem,
    val isSelected: Boolean = false,
    val isEnabled: Boolean = true
)

enum class DockItem(
    val titleRes: StringResource,
    val iconRes: DrawableResource
) {
    CHATS(
        titleRes = Res.string.dock_chats,
        iconRes = Res.drawable.search
    ),
    CONTACTS(
        titleRes = Res.string.dock_contacts,
        iconRes = Res.drawable.profile
    ),
    SETTINGS(
        titleRes = Res.string.dock_settings,
        iconRes = Res.drawable.exit
    )
}
