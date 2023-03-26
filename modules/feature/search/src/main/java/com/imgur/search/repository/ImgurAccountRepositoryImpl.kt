package com.imgur.search.repository

import com.imgur.network_api.api.ImgurApi
import com.imgur.network_api.extension.Response
import com.imgur.network_api.extension.safeRun
import com.imgur.network_api.models.AccountImages
import javax.inject.Inject

class ImgurAccountRepositoryImpl @Inject constructor(
    private val accountApi: ImgurApi
) : ImgurAccountRepository {

    override suspend fun getAccountImages(): Response<AccountImages> {
        return safeRun { accountApi.getAccountImages() }
    }
}
