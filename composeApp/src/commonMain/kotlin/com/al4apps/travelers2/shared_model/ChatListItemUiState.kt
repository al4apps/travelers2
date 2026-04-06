package com.al4apps.travelers2.shared_model

import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import travelers2.composeapp.generated.resources.Res
import travelers2.composeapp.generated.resources.chat_type_channel
import travelers2.composeapp.generated.resources.chat_type_private

data class ChatListItemUiState(
    val id: String = "",
    val title: String,
    val type: ChatType,
    val lastMessage: String? = null,
    val avatar: ChatAvatarUiState? = null
) {
    companion object {
        val SAMPLE = ChatListItemUiState(
            id = "chat_1",
            title = "Ivan Ivanov",
            type = ChatType.PRIVATE,
            lastMessage = "Коллеги, пришлите пожалуйста последний билд на проверку."
        )
    }
}

data class ChatAvatarUiState(
    val image: DrawableResource? = null,
    val initials: String? = null
)

enum class ChatType(
    val titleRes: StringResource
) {
    PRIVATE(titleRes = Res.string.chat_type_private),
    CHANNEL(titleRes = Res.string.chat_type_channel)
}
