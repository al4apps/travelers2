package com.al4apps.travelers2.features.home

import com.al4apps.travelers2.shared_model.DockItem
import com.al4apps.travelers2.shared_model.DockItemUiState
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value

interface HomeComponent {
    val uiState: Value<HomeUiState>
    val stack: Value<ChildStack<HomeConfig, HomeChild>>

    fun onRetryClick()

    fun onDockItemClick(item: DockItem)

    fun getDockItems(): List<DockItemUiState>
}
