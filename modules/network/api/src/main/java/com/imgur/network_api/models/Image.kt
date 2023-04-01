package com.imgur.network_api.models

import com.squareup.moshi.Json

data class Image(
    val id: String = "",
    val title: String = "",
    val description: String = "",
    val datetime: Long = 0,
    val type: String = "",
    val animated: Boolean = false,
    val width: Int = 0,
    val height: Int = 0,
    val size: Long = 0,
    val views: Int = 0,
    val bandwidth: Long = 0,
    val vote: String = "",
    val favorite: Boolean = false,
    val nsfw: Boolean = false,
    val section: String = "",
    @Json(name = "account_url")
    val accountUrl: String = "",
    @Json(name = "account_id")
    val accountId: String = "",
    @Json(name = "is_ad")
    val isAd: Boolean = false,
    @Json(name = "in_most_viral")
    val inMostViral: Boolean = false,
    @Json(name = "has_sound")
    val hasSound: Boolean = false,
    val tags: List<Tag> = emptyList(),
    @Json(name = "ad_type")
    val adType: Int = 0,
    @Json(name = "ad_url")
    val adUrl: String = "",
    val edited: Int = 0,
    @Json(name = "in_gallery")
    val inGallery: Boolean = false,
    val link: String = "",
    @Json(name = "comment_count")
    val commentCount: Int = 0,
    @Json(name = "favorite_count")
    val favoriteCount: Int = 0,
    val ups: Int = 0,
    val downs: Int = 0,
    val points: Int = 0,
    val score: Int = 0,
) {
    fun isEmpty() = this === EMPTY

    companion object {
        val EMPTY = Image()
    }
}
