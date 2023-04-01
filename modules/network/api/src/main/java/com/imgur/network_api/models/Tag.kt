package com.imgur.network_api.models

import com.squareup.moshi.Json

data class Tag(
    val name: String = "",
    @Json(name = "display_name")
    val displayName: String = "",
    val followers: Int = 0,
    @Json(name = "total_items")
    val totalItems: Int = 0,
    val following: Boolean = false,
    @Json(name = "is_whitelisted")
    val isWhitelisted: Boolean = false,
    @Json(name = "background_hash")
    val backgroundHash: String = "",
    @Json(name = "thumbnail_hash")
    val thumbnailHash: String = "",
    val accent: String = "",
    @Json(name = "background_is_animated")
    val backgroundIsAnimated: Boolean = false,
    @Json(name = "thumbnail_is_animated")
    val thumbnailIsAnimated: Boolean = false,
    @Json(name = "is_promoted")
    val isPromoted: Boolean = false,
    val description: String = "",
    @Json(name = "logo_hash")
    val logoHash: String = "",
    @Json(name = "logo_destination_url")
    val logoDestinationUrl: String = "",
    @Json(name = "description_annotations")
    val descriptionAnnotations: Any = Any()
) {
    fun isEmpty() = this === EMPTY

    companion object {
        val EMPTY = Tag()
    }
}
