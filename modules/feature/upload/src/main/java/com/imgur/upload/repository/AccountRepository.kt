package com.imgur.upload.repository

import android.net.Uri
import com.imgur.network_api.extension.Response
import com.imgur.network_api.models.AccountImageResult
import com.imgur.network_api.models.UploadResult

interface AccountRepository {
    suspend fun uploadImage(uri: Uri): Response<UploadResult>

    suspend fun loadAccountImages(): Response<AccountImageResult>
}
