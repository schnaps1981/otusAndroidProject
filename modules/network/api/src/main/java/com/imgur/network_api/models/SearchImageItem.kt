package com.imgur.network_api.models

import com.squareup.moshi.Json

data class SearchImageItem(
    val id: String = "",
    val title: String = "",
    val description: String = "",
    val datetime: Long = 0,
    val cover: String = "",
    @Json(name = "cover_width")
    val coverWidth: Int = 0,
    @Json(name = "cover_height")
    val coverHeight: Int = 0,
    @Json(name = "account_url")
    val accountUrl: String = "",
    @Json(name = "account_id")
    val accountId: Long = 0,
    val privacy: String = "",
    val layout: String = "",
    val views: Int = 0,
    val link: String = "",
    val ups: Int = 0,
    val downs: Int = 0,
    val points: Int = 0,
    val score: Int = 0,
    @Json(name = "is_album")
    val isAlbum: Boolean = false,
    val vote: String = "",
    val favorite: Boolean = false,
    val nsfw: Boolean = false,
    val section: String = "",
    @Json(name = "comment_count")
    val commentCount: Int = 0,
    @Json(name = "favorite_count")
    val favoriteCount: Int = 0,
    val topic: String = "",
    @Json(name = "topic_id")
    val topicId: Int = 0,
    @Json(name = "images_count")
    val imagesCount: Int = 0,
    @Json(name = "in_gallery")
    val inGallery: Boolean = false,
    @Json(name = "is_ad")
    val isAd: Boolean = false,
    val tags: List<Tag> = emptyList(),
    @Json(name = "ad_type")
    val adType: Int = 0,
    @Json(name = "ad_url")
    val adUrl: String = "",
    @Json(name = "in_most_viral")
    val inMostViral: Boolean = false,
    @Json(name = "include_album_ads")
    val includeAlbumAds: Boolean = false,
    val images: List<Image> = emptyList(),
    @Json(name = "ad_config")
    val adConfig: AdConfig = AdConfig.EMPTY
) {
    fun isEmpty() = this === EMPTY

    fun findImageById(): String {
        return images.find { it.id == cover }?.link ?: ""
    }

    companion object {
        val EMPTY = SearchImageItem()
    }
}
