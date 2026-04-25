package com.al4apps.travelers2.features.root

import com.al4apps.travelers2.core.CoroutineFeature
import com.al4apps.travelers2.core.CoroutineFeatureImpl
import com.al4apps.travelers2.di.RootDiContainer
import com.al4apps.travelers2.features.home.DefaultHomeComponent
import com.al4apps.travelers2.features.onboarding.DefaultOnboardingComponent
import com.al4apps.travelers2.features.root.domain.RootStartDestination
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.replaceAll

class DefaultRootComponent(
    componentContext: ComponentContext,
    private val diContainer: RootDiContainer,
) : RootComponent,
    ComponentContext by componentContext,
    CoroutineFeature by CoroutineFeatureImpl(componentContext) {

    private val navigation = StackNavigation<RootConfig>()

    override val stack = childStack(
        source = navigation,
        serializer = RootConfig.serializer(),
        initialConfiguration = diContainer.getInitialRouteUseCase().toRootConfig(),
        handleBackButton = true,
        childFactory = ::createChild,
    )

    private fun createChild(
        config: RootConfig,
        componentContext: ComponentContext,
    ): RootChild =
        when (config) {
            RootConfig.Home -> RootChild.Home(
                component = createHomeComponent(componentContext),
            )

            RootConfig.Onboarding -> RootChild.Onboarding(
                component = createOnboardingComponent(componentContext),
            )
        }

    private fun createHomeComponent(componentContext: ComponentContext): DefaultHomeComponent {
        val homeDiContainer = diContainer.createHomeDiContainer()

        return DefaultHomeComponent(
            componentContext = componentContext,
            initializeHomeUseCase = homeDiContainer.initializeHomeUseCase,
            handleDriverContactActionUseCase = homeDiContainer.handleDriverContactActionUseCase,
            uiAssembler = homeDiContainer.homeUiAssembler,
        )
    }

    private fun createOnboardingComponent(componentContext: ComponentContext): DefaultOnboardingComponent {
        val onboardingDiContainer = diContainer.createOnboardingDiContainer()

        return DefaultOnboardingComponent(
            componentContext = componentContext,
            getOnboardingPlaceholderUseCase = onboardingDiContainer.getOnboardingPlaceholderUseCase,
            uiAssembler = onboardingDiContainer.onboardingUiAssembler,
            onCompleted = ::onOnboardingCompleted,
        )
    }

    private fun onOnboardingCompleted() {
        diContainer.completeFirstLaunchUseCase()
        navigation.replaceAll(RootConfig.Home)
    }
}

private fun RootStartDestination.toRootConfig(): RootConfig =
    when (this) {
        RootStartDestination.HOME -> RootConfig.Home
        RootStartDestination.ONBOARDING -> RootConfig.Onboarding
    }
