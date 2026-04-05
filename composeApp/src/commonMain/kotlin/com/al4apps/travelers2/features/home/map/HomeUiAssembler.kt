package com.al4apps.travelers2.features.home.map

import com.al4apps.travelers2.core.ui.UiText
import com.al4apps.travelers2.features.home.HomeUiState
import com.al4apps.travelers2.features.home.HomeVmState

class HomeUiAssembler {

    fun assemble(vmState: HomeVmState): HomeUiState =
        HomeUiState(
            isLoading = vmState.isLoading,
            title = UiText.Plain(vmState.title.ifBlank { "Home" }),
            message = UiText.Plain(vmState.message),
            errorMessage = vmState.errorMessage?.let(UiText::Plain),
        )
}
