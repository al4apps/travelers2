package com.al4apps.travelers2.core

import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.lifecycle.LifecycleOwner
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlin.coroutines.CoroutineContext

fun <VM : Any, UI : Any> StateStore<VM>.toUiState(
    scope: CoroutineScope,
    assemble: (VM) -> UI,
): Value<UI> {
    val mutableValue = MutableValue(assemble(value))
    stateFlow.onEach { vm -> mutableValue.value = assemble(vm) }.launchIn(scope)
    return mutableValue
}

@Deprecated(
    message = "Typo in method name, use toUiState instead.",
    replaceWith = ReplaceWith("toUiState(scope, assemble)"),
)
fun <VM : Any, UI : Any> StateStore<VM>.toUiSate(
    scope: CoroutineScope,
    assemble: (VM) -> UI,
): Value<UI> = toUiState(scope = scope, assemble = assemble)

fun LifecycleOwner.coroutineScope(context: CoroutineContext): CoroutineScope {
    return CoroutineScope(context, lifecycle)
}
