package com.imgur.search.repository

import com.imgur.database_api.dto.FavoriteItem
import com.imgur.network_api.extension.Response
import com.imgur.network_api.models.SearchImageResult

interface SearchRepository {

    suspend fun searchByQuery(pageNum: Int, query: String): Response<SearchImageResult>

    suspend fun addToFavorite(value: FavoriteItem): Long
}
