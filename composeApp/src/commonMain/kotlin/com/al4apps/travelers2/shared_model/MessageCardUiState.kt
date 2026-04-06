package com.al4apps.travelers2.shared_model

import org.jetbrains.compose.resources.DrawableResource
import travelers2.composeapp.generated.resources.Res
import travelers2.composeapp.generated.resources.checkmark

data class MessageCardUiState(
    val id: String,
    val direction: MessageDirection,
    val text: String,
    val time: String,
    val senderName: String? = null,
    val avatar: MessageAvatarUiState? = null,
    val statusIcon: DrawableResource? = null,
) {
    companion object {
        val SAMPLE_INCOMING = MessageCardUiState(
            id = "incoming_sample",
            direction = MessageDirection.INCOMING,
            text = "Привет! Давай обсудим детали встречи.",
            time = "09:39",
            senderName = "Иван Иванов"
        )
        val SAMPLE_OUTGOING = MessageCardUiState(
            id = "outgoing_sample",
            direction = MessageDirection.OUTGOING,
            text = "Ок, подходи через час, будет готово.",
            time = "11:22",
            statusIcon = Res.drawable.checkmark
        )
    }
}

data class MessageAvatarUiState(
    val image: DrawableResource? = null,
    val initials: String? = null
)

enum class MessageDirection {
    INCOMING,
    OUTGOING
}
