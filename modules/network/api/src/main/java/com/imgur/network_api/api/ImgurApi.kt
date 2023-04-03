package com.imgur.network_api.api

import com.imgur.network_api.models.SearchImageResult
import com.imgur.network_api.models.UploadResult
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

interface ImgurApi {

    @GET("gallery/search/time/all/{page}/?q_type=jpg")
    suspend fun searchImages(
        @Path("page") pageNum: Int,
        @Query("q") query: String
    ): SearchImageResult

    @Multipart
    @POST("/3/upload")
    suspend fun uploadImage(
        @Part image: MultipartBody.Part?,
        @Part("name") name: RequestBody? = null
    ): UploadResult
}
