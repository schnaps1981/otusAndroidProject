package com.imgur.search

import com.imgur.database_api.dto.FavoriteItem
import com.imgur.network_api.models.SearchImageResult

object SearchStubs {
    val successSearchByQueryItem =
        SearchImageResult(data = emptyList(), success = true, status = 200)

    val favoriteItem = FavoriteItem(
        imageId = "123",
        imageUrl = "url",
        title = "image"
    )

    fun getSuccessSearchByQuery(): SearchImageResult {
        return successSearchByQueryItem
    }
}