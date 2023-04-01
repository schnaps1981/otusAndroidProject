package com.imgur.network_api.api

import com.imgur.network_api.models.SearchImageResult
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ImgurApi {

    @GET("gallery/search/time/all/{page}/?q_type=jpg")
    suspend fun searchImages(
        @Path("page") pageNum: Int,
        @Query("q") query: String
    ): SearchImageResult
}
