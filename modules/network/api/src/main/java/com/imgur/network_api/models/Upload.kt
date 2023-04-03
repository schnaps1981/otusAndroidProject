package com.imgur.network_api.models

import com.squareup.moshi.Json

data class Upload(
    @Json(name = "account_id")
    val accountId: Int = 0,
    @Json(name = "account_url")
    val accountUrl: String = "",
    @Json(name = "ad_type")
    val adType: Int = 0,
    @Json(name = "ad_url")
    val adUrl: String = "",
    val animated: Boolean = false,
    val bandwidth: Int = 0,
    val datetime: Long = 0,
    @Json(name = "deletehash")
    val deleteHash: String = "",
    val description: String = "",
    val favorite: Boolean = false,
    @Json(name = "has_sound")
    val hasSound: Boolean = false,
    val height: Int = 0,
    val hls: String = "",
    val id: String = "",
    val inGallery: Boolean = false,
    val inMostViral: Boolean = false,
    val isAd: Boolean = false,
    val link: String = "",
    val mp4: String = "",
    val name: String = "",
    val size: Int = 0,
    val tags: List<Tag> = emptyList(),
    val title: String = "",
    val type: String = "",
    val views: Int = 0,
    val width: Int = 0
) {

    fun isEmpty() = this === EMPTY

    companion object {
        val EMPTY = Upload()
    }
}
