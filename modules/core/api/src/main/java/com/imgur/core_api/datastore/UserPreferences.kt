package com.imgur.core_api.datastore

import com.imgur.core_api.models.ImgurAccessToken
import kotlinx.coroutines.flow.Flow

interface UserPreferences {
    val accessToken: Flow<String?>

    val refreshToken: Flow<String?>

    val expiresToken: Flow<Long?>

    suspend fun saveTokens(tokenModel: ImgurAccessToken)

    suspend fun clear()
}
