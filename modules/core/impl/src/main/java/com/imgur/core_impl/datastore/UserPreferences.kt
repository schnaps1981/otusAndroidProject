package com.imgur.core_impl.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.imgur.core_api.datastore.UserPreferences
import com.imgur.core_api.models.ImgurAccessToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private const val dataStoreFile: String = "securePref"

class UserPreferencesImpl @Inject constructor(
    private val context: Context,
    private val crypto: Crypto,
    secretKey: ISecretKey
) : UserPreferences {

    private val key = secretKey.getSecretKey()

    override val accessToken: Flow<String?>
        get() = context.dataStore.data.map { preferences ->
            preferences[ACCESS_TOKEN_KEY]?.let { crypto.decode(it, key) }
        }

    override val refreshToken: Flow<String?>
        get() = context.dataStore.data.map { preferences ->
            preferences[REFRESH_TOKEN_KEY]?.let { crypto.decode(it, key) }
        }

    override val expiresToken: Flow<Long?>
        get() = context.dataStore.data.map {preferences->
            preferences[TOKEN_EXPIRES_KEY]
        }

    override suspend fun saveTokens(tokenModel: ImgurAccessToken) {
        context.dataStore.edit { preferences ->
            preferences[ACCESS_TOKEN_KEY] = crypto.encode(tokenModel.accessToken, key)
            preferences[REFRESH_TOKEN_KEY] = crypto.encode(tokenModel.refreshToken, key)
            preferences[TOKEN_EXPIRES_KEY] = tokenModel.getExpires().time
        }
    }

    override suspend fun clear() {
        context.dataStore.edit { preferences ->
            preferences.clear()
        }
    }

    companion object {
        private val Context.dataStore by preferencesDataStore(name = dataStoreFile)
        private val ACCESS_TOKEN_KEY = stringPreferencesKey("ACCESS_TOKEN_KEY")
        private val REFRESH_TOKEN_KEY = stringPreferencesKey("REFRESH_TOKEN_KEY")
        private val TOKEN_EXPIRES_KEY = longPreferencesKey("TOKEN_EXPIRES_KEY")
    }
}
