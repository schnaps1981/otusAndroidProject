package com.imgur.network_impl.utils

import com.imgur.core_api.Constants
import com.imgur.core_api.datastore.UserPreferences
import com.imgur.core_api.models.ImgurAccessToken
import com.imgur.network_api.extension.safeRun
import com.imgur.network_api.models.RefreshTokenResponse
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

class AuthAuthenticator @Inject constructor(
    private val userPreferences: UserPreferences,
    private val refreshTokenApi: ImgurRefreshTokenApi
) : Authenticator {

    private val refreshToken = runBlocking {
        userPreferences.refreshToken.first()
    }

    override fun authenticate(route: Route?, response: Response): Request = runBlocking {
        when (val newToken = refreshToken()) {
            is com.imgur.network_api.extension.Response.Success -> {
                val token = ImgurAccessToken(
                    accessToken = newToken.value.accessToken,
                    refreshToken = newToken.value.refreshToken,
                    tokenType = newToken.value.tokenType,
                    expiresIn = newToken.value.expiresIn.toString(),
                    accountUsername = newToken.value.accountUsername,
                    accountId = newToken.value.accountId.toString()
                )

                userPreferences.saveTokens(token)

                return@runBlocking response.request.newBuilder()
                    .header("Authorization", "Bearer ${newToken.value.accessToken}")
                    .build()
            }

            else -> {
                userPreferences.clear()

                return@runBlocking response.request.newBuilder().build()
            }
        }
    }

    private suspend fun refreshToken(): com.imgur.network_api.extension.Response<RefreshTokenResponse> {
        val params = paramsMap + mapOf(
            "refresh_token" to (refreshToken ?: "").toRequestBody()
        )

        return safeRun { refreshTokenApi.getNewToken(params) }
    }

    companion object {
        private val paramsMap = mapOf(
            "client_id" to Constants.imgurAppId.toRequestBody(),
            "client_secret" to Constants.imgurAppSecret.toRequestBody(),
            "grant_type" to "refresh_token".toRequestBody()
        )
    }
}
