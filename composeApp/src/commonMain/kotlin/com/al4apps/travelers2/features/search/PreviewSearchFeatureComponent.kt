package com.al4apps.travelers2.features.search

import com.al4apps.travelers2.features.search_form.PreviewSearchFormComponent
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value

class PreviewSearchFeatureComponent : SearchFeatureComponent {
    override val stack: Value<ChildStack<SearchConfig, SearchChild>> = MutableValue(
        ChildStack(
            configuration = SearchConfig.Form,
            instance = SearchChild.Form(
                component = PreviewSearchFormComponent(),
            ),
        ),
    )
}
