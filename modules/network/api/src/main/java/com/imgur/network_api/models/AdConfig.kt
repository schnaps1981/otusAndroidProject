package com.imgur.network_api.models

import com.squareup.moshi.Json

data class AdConfig(
    val safeFlags: List<String> = emptyList(),
    val highRiskFlags: List<String> = emptyList(),
    val unsafeFlags: List<String> = emptyList(),
    val wallUnsafeFlags: List<String> = emptyList(),
    val showsAds: Boolean = false,
    val showAdLevel: Int = 0,
    @Json(name = "nsfw_score")
    val nsfwScore: Int = 0
) {
    fun isEmpty() = this === EMPTY

    companion object {
        val EMPTY = AdConfig()
    }
}
