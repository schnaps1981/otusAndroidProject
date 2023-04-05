package com.imgur.core_api.models

import android.net.Uri
import java.lang.Long.parseLong
import java.util.*

data class ImgurAccessToken(
    val accessToken: String = "",
    val refreshToken: String = "",
    val tokenType: String = "",
    val expiresIn: String = "",
    val accountUsername: String = "",
    val accountId: String = "",
    val createdAt: Date = Date()
) {
    fun isEmpty() = this === EMPTY

    fun getExpires(): Date {
        var creationTime = createdAt.time

        creationTime += if (expiresIn.isNotEmpty()) {
            parseLong(expiresIn) * 1000L
        } else {
            parseLong("2419200") * 1000L //если нет срока истечения токена, принимаем его за 28 дней, так в доках
        }

        return Date(creationTime)
    }

    fun isExpired(): Boolean {
        val now = Date()

        return !now.before(getExpires())
    }

    companion object {
        val EMPTY = ImgurAccessToken()

        fun valueOf(value: String?): ImgurAccessToken {
            val fragment = Uri.parse("?$value")

            return ImgurAccessToken(
                accessToken = fragment.getQueryParameter("access_token") ?: "",
                tokenType = fragment.getQueryParameter("token_type") ?: "",
                expiresIn = fragment.getQueryParameter("expires_in") ?: "",
                refreshToken = fragment.getQueryParameter("refresh_token") ?: "",
                accountUsername = fragment.getQueryParameter("account_username") ?: "",
                accountId = fragment.getQueryParameter("account_id") ?: ""
            )
        }
    }
}
