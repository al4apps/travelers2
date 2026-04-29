package com.al4apps.travelers2.core.auth

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * Stub auth repository. By default returns true (authenticated) so existing flows are not affected.
 * To preview the unauthenticated state on the create-trip screen, change [DEFAULT_AUTH_STATE] to false.
 */
class InMemoryAuthRepository : AuthRepository {

    private val state = MutableStateFlow(DEFAULT_AUTH_STATE)

    override fun observeAuthState(): Flow<Boolean> = state.asStateFlow()

    private companion object {
        const val DEFAULT_AUTH_STATE = true
    }
}
