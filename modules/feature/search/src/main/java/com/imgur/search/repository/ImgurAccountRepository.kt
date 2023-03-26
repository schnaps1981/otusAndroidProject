package com.imgur.search.repository

import com.imgur.network_api.models.AccountImages
import com.imgur.network_api.extension.Response

interface ImgurAccountRepository {

    suspend fun getAccountImages(): Response<AccountImages>
}
