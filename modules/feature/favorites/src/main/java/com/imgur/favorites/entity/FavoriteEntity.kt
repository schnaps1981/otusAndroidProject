package com.imgur.favorites.entity

import com.imgur.database_api.dto.FavoriteItem

data class FavoriteEntity(
    val pk: Long = 0L,
    val id: String = "",
    val url: String = "",
    val title: String = ""
) {
    fun isEmpty() = this === EMPTY

    companion object {
        val EMPTY = FavoriteEntity()

        fun valueOf(value: FavoriteItem): FavoriteEntity {
            return FavoriteEntity(
                pk = value.pk,
                id = value.imageId,
                url = value.imageUrl,
                title = value.title
            )
        }
    }
}
