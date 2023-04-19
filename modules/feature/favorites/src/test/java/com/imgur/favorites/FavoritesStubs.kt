package com.imgur.favorites

import com.imgur.favorites.entity.FavoriteEntity

object FavoritesStubs {

    val favoritesList = listOf(
        FavoriteEntity(
            pk = 1L,
            id = "id1",
            url = "url1",
            title = "title1"
        ),
        FavoriteEntity(
            pk = 2L,
            id = "id2",
            url = "url2",
            title = "title2"
        ),
    )
}
