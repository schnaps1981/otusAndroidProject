package com.imgur.network_api.models

import com.squareup.moshi.Json

data class RefreshTokenResponse(
    @Json(name = "access_token")
    val accessToken: String = "",
    @Json(name = "refresh_token")
    val refreshToken: String = "",
    @Json(name = "token_type")
    val tokenType: String = "",
    @Json(name = "expires_in")
    val expiresIn: Long = 0L,
    @Json(name = "account_username")
    val accountUsername: String = "",
    @Json(name = "account_id")
    val accountId: Long = 0,
    val scope: String = ""
) {
    fun isEmpty() = this === EMPTY

    companion object {
        val EMPTY = RefreshTokenResponse()
    }
}
