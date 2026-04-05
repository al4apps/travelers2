package com.al4apps.travelers2.features.home

import com.al4apps.travelers2.core.CoroutineFeature
import com.al4apps.travelers2.core.CoroutineFeatureImpl
import com.al4apps.travelers2.core.StateStore
import com.al4apps.travelers2.core.toUiState
import com.al4apps.travelers2.features.home.domain.usecase.GetHomePlaceholderUseCase
import com.al4apps.travelers2.features.home.domain.usecase.InitializeHomeUseCase
import com.al4apps.travelers2.features.home.map.HomeUiAssembler
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value
import kotlinx.coroutines.launch

class DefaultHomeComponent(
    componentContext: ComponentContext,
    private val initializeHomeUseCase: InitializeHomeUseCase,
    private val getHomePlaceholderUseCase: GetHomePlaceholderUseCase,
    private val uiAssembler: HomeUiAssembler = HomeUiAssembler(),
) : HomeComponent,
    ComponentContext by componentContext,
    CoroutineFeature by CoroutineFeatureImpl(componentContext) {

    private val vmStateStore = StateStore(
        initialState = stateKeeper.consume(
            key = STATE_KEY,
            strategy = HomeVmState.serializer(),
        ) ?: HomeVmState(),
    )

    override val uiState: Value<HomeUiState> =
        vmStateStore.toUiState(
            scope = featureScope,
            assemble = uiAssembler::assemble,
        )

    init {
        stateKeeper.register(
            key = STATE_KEY,
            strategy = HomeVmState.serializer(),
            supplier = vmStateStore::value,
        )
        bootstrap()
    }

    override fun onRetryClick() {
        bootstrap()
    }

    private fun bootstrap() {
        vmStateStore.updateState {
            it.copy(
                isLoading = true,
                errorMessage = null,
            )
        }

        featureScope.launch {
            runCatching {
                initializeHomeUseCase()
                getHomePlaceholderUseCase()
            }.onSuccess { placeholder ->
                vmStateStore.updateState {
                    it.copy(
                        isLoading = false,
                        title = placeholder.title,
                        message = placeholder.message,
                        errorMessage = null,
                    )
                }
            }.onFailure { throwable ->
                vmStateStore.updateState {
                    it.copy(
                        isLoading = false,
                        errorMessage = throwable.message ?: "Home initialization failed.",
                    )
                }
            }
        }
    }

    private companion object {
        const val STATE_KEY = "home_vm_state"
    }
}
