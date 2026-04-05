package com.al4apps.travelers2.features.root

import com.al4apps.travelers2.features.home.PreviewHomeComponent
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value

class PreviewRootComponent : RootComponent {
    override val stack: Value<ChildStack<RootConfig, RootChild>> =
        MutableValue(
            ChildStack(
                configuration = RootConfig.Home,
                instance = RootChild.Home(
                    component = PreviewHomeComponent(),
                ),
            ),
        )
}
