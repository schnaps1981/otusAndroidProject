package com.imgur.search.entity

import com.imgur.network_api.models.SearchImageItem

data class SearchItemEntity(
    val id: String = "",
    val imageUrl: String = "",
    val title: String = ""
) {
    fun isEmpty() = this === EMPTY

    companion object {
        val EMPTY = SearchItemEntity()

        fun valueOf(value: SearchImageItem): SearchItemEntity {
            return SearchItemEntity(
                id = value.id,
                imageUrl = value.findImageById(),
                title = value.title
            )
        }
    }
}
