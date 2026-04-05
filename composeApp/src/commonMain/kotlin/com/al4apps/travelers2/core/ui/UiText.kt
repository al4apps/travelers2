package com.al4apps.travelers2.core.ui

import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

sealed interface UiText {
    data class Plain(
        val value: String,
    ) : UiText

    data class Resource(
        val value: StringResource,
        val args: List<Any> = emptyList()
    ) : UiText
}

@Composable
fun UiText.asString(): String =
    when (this) {
        is UiText.Plain -> {
            value
        }

        is UiText.Resource -> {
            if (args.isEmpty()) {
                stringResource(value)
            } else {
                stringResource(value, *args.toTypedArray())
            }
        }
    }
