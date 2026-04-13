package com.al4apps.travelers2.features.search

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value

interface SearchFeatureComponent {
    val stack: Value<ChildStack<SearchConfig, SearchChild>>
}
