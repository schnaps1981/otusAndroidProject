package com.imgur.network_api.api

import com.imgur.network_api.models.AccountImages
import retrofit2.http.GET

interface ImgurApi {

    @GET("account/me/images/")
    suspend fun getAccountImages(): AccountImages
}
