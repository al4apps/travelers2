package com.al4apps.travelers2.core.dialog

import androidx.compose.runtime.Stable
import com.arkivanov.decompose.value.Value

@Stable
interface DialogContentComponent {
    val dialogContentState: Value<DialogContentState>

    fun onDismissRequest()
}
