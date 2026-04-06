package com.al4apps.travelers2.shared_model

import com.al4apps.travelers2.core.ui.UiText
import org.jetbrains.compose.resources.DrawableResource
import travelers2.composeapp.generated.resources.Res
import travelers2.composeapp.generated.resources.arrow_right
import travelers2.composeapp.generated.resources.plus
import travelers2.composeapp.generated.resources.star

data class MessageInputBarUiState(
    val text: String = "",
    val placeholder: UiText = UiText.Plain("Сообщение"),
    val leadingIcon: DrawableResource = Res.drawable.star,
    val actionIcon: DrawableResource = Res.drawable.plus,
    val actionType: MessageInputActionType = MessageInputActionType.ATTACHMENT,
) {
    companion object {
        val SAMPLE = MessageInputBarUiState(
            text = "",
            placeholder = UiText.Plain("Сообщение")
        )

        val SAMPLE_FILLED = MessageInputBarUiState(
            text = "Привет! Как дела?",
            placeholder = UiText.Plain("Сообщение"),
            actionIcon = Res.drawable.arrow_right,
            actionType = MessageInputActionType.SEND,
        )
    }
}

enum class MessageInputActionType {
    ATTACHMENT,
    SEND,
}
