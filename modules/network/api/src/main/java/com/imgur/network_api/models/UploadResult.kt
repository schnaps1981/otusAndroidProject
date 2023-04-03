package com.imgur.network_api.models

import com.squareup.moshi.Json

data class UploadResult(
    @Json(name = "data")
    val upload: Upload = Upload.EMPTY,
    @Json(name = "status")
    val status: Int = 0,
    @Json(name = "success")
    val success: Boolean = false
) {
    fun isEmpty() = this == EMPTY

    companion object {
        val EMPTY = UploadResult()
    }
}
