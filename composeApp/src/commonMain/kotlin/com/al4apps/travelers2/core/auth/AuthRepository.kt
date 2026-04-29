package com.al4apps.travelers2.core.auth

import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun observeAuthState(): Flow<Boolean>
}
