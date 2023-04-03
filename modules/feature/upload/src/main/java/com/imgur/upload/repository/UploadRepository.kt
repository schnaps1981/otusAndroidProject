package com.imgur.upload.repository

import android.net.Uri
import com.imgur.network_api.extension.Response
import com.imgur.network_api.models.UploadResult

interface UploadRepository {
    suspend fun uploadImage(uri: Uri): Response<UploadResult>
}
