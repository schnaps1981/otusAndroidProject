package com.imgur.network_impl.utils

import com.imgur.network_api.models.RefreshTokenResponse
import okhttp3.RequestBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PartMap

interface ImgurRefreshTokenApi {
    @JvmSuppressWildcards
    @POST("/oauth2/token/")
    @Multipart
    suspend fun getNewToken(@PartMap params: Map<String, RequestBody>): RefreshTokenResponse
}
