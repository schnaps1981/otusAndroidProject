package com.imgur.network_api.models

data class AccountImages(
    val data: List<AccountData> = emptyList(),
    val success: Boolean = false,
    val status: Int = 0
)

data class AccountData(
    val id: String = "",
    val title: String = "",
    val description: String = "",
    val datetime: Long = 0,
    val type: String = "",
    val animated: Boolean = false,
    val width: Int = 0,
    val height: Int = 0,
    val size: Long = 0L,
    val views: Int = 0,
    val bandwidth: Long = 0,
    val vote: Int = 0,
    val favorite: Boolean = false,
    val nsfw: String = "",
    val section: String = "",
    val account_url: String = "",
    val account_id: Long = 0L,
    val is_ad: Boolean = false,
    val in_most_viral: Boolean = false,
    val has_sound: Boolean = false,
    val tags: List<String> = emptyList(),
    val ad_type: Int = 0,
    val ad_url: String = "",
    val edited: String = "",
    val in_gallery: Boolean = false,
    val deletehash: String = "",
    val name: String = "",
    val link: String = ""
)
