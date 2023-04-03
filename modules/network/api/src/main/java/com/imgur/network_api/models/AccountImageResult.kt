package com.imgur.network_api.models

import com.squareup.moshi.Json

data class AccountImageResult(
    @Json(name = "data")
    val items: List<AccountImageItem> = emptyList(),
    val status: Int = 0,
    val success: Boolean = false
) {
    fun isEmpty() = this == EMPTY

    companion object {
        val EMPTY = AccountImageResult()
    }
}
