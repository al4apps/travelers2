package com.al4apps.travelers2.features.home.map

import com.al4apps.travelers2.core.ui.UiText
import com.al4apps.travelers2.features.home.model.HomeUiState
import com.al4apps.travelers2.features.home.model.HomeVmState

class HomeUiAssembler {

    fun assemble(vmState: HomeVmState): HomeUiState =
        HomeUiState(
            isLoading = vmState.isLoading,
            isDockVisible = vmState.isDockVisible,
            errorMessage = vmState.errorMessage?.let(UiText::Plain),
        )
}
