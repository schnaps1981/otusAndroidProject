package com.imgur.network_api.models

import com.squareup.moshi.Json

data class ImageDeleteResponse(
    @Json(name = "data")
    val isDeleteSuccess: Boolean = false,
    @Json(name = "success")
    val isResponseSuccess: Boolean = false,
    val status: Int = 0
) {
    fun isEmpty() = this === EMPTY

    companion object {
        val EMPTY = ImageDeleteResponse()
    }
}
