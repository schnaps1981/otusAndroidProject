package com.imgur.network_api.models

data class SearchImageResult(
    val data: List<SearchImageItem> = emptyList(),
    val success: Boolean = false,
    val status: Int = 0
) {
    fun isEmpty() = this === EMPTY

    companion object {
        val EMPTY = SearchImageResult()
    }
}
