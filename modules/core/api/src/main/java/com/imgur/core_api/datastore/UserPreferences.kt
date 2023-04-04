package com.imgur.core_api.datastore

import kotlinx.coroutines.flow.Flow

interface UserPreferences {
    val accessToken: Flow<String?>

    val refreshToken: Flow<String?>

    suspend fun saveTokens(accessToken: String?, refreshToken: String?)

    suspend fun clear()
}
