package com.imgur.search.repository

import com.imgur.network_api.api.ImgurApi
import com.imgur.network_api.extension.Response
import com.imgur.network_api.extension.safeRun
import com.imgur.network_api.models.SearchImageResult
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val accountApi: ImgurApi
) : SearchRepository {

    override suspend fun searchByQuery(pageNum: Int, query: String): Response<SearchImageResult> {
        return safeRun { accountApi.searchImages(pageNum, query) }
    }
}
