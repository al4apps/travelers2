package com.al4apps.travelers2.features.onboarding

import com.al4apps.travelers2.core.CoroutineFeature
import com.al4apps.travelers2.core.CoroutineFeatureImpl
import com.al4apps.travelers2.core.StateStore
import com.al4apps.travelers2.core.toUiState
import com.al4apps.travelers2.features.onboarding.domain.model.OnboardingPlaceholder
import com.al4apps.travelers2.features.onboarding.domain.usecase.GetOnboardingPlaceholderUseCase
import com.al4apps.travelers2.features.onboarding.map.OnboardingUiAssembler
import com.al4apps.travelers2.features.onboarding.model.OnboardingUiState
import com.al4apps.travelers2.features.onboarding.model.OnboardingVmState
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value

class DefaultOnboardingComponent(
    componentContext: ComponentContext,
    private val getOnboardingPlaceholderUseCase: GetOnboardingPlaceholderUseCase,
    private val uiAssembler: OnboardingUiAssembler = OnboardingUiAssembler(),
    private val onCompleted: () -> Unit,
) : OnboardingComponent,
    ComponentContext by componentContext,
    CoroutineFeature by CoroutineFeatureImpl(componentContext) {

    private val vmStateStore = StateStore(
        initialState = stateKeeper.consume(
            key = STATE_KEY,
            strategy = OnboardingVmState.serializer(),
        ) ?: getOnboardingPlaceholderUseCase().toVmState(),
    )

    override val uiState: Value<OnboardingUiState> =
        vmStateStore.toUiState(
            scope = featureScope,
            assemble = uiAssembler::assemble,
        )

    init {
        stateKeeper.register(
            key = STATE_KEY,
            strategy = OnboardingVmState.serializer(),
            supplier = vmStateStore::value,
        )
    }

    override fun onContinueClick() {
        onCompleted()
    }

    private companion object {
        const val STATE_KEY = "onboarding_vm_state"
    }
}

private fun OnboardingPlaceholder.toVmState(): OnboardingVmState =
    OnboardingVmState(
        title = title,
        message = message,
        buttonLabel = buttonLabel,
    )
