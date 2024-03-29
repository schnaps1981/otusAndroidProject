package com.imgur.upload.repository

import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import com.imgur.network_api.api.ImgurApi
import com.imgur.network_api.extension.Response
import com.imgur.network_api.extension.safeRun
import com.imgur.network_api.models.AccountImageResult
import com.imgur.network_api.models.ImageDeleteResponse
import com.imgur.network_api.models.UploadResult
import com.imgur.upload.entity.ReadFileResult
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    context: Context,
    private val imgurApi: ImgurApi
) : AccountRepository {
    private val contentResolver = context.contentResolver

    override suspend fun uploadImage(uri: Uri): Response<UploadResult> {

        val uploadFile = tryReadFile(uri)

        val filePart = MultipartBody.Part.createFormData(
            "image",
            uploadFile.fileName,
            uploadFile.bytes.toRequestBody()
        )

        return safeRun { imgurApi.uploadImage(filePart) }
    }

    override suspend fun loadAccountImages(): Response<AccountImageResult> {
        return safeRun { imgurApi.loadAccountImages() }
    }

    override suspend fun deleteImage(hash: String): Response<ImageDeleteResponse> {
        return safeRun { imgurApi.deleteImage(hash) }
    }

    private fun tryReadFile(uri: Uri): ReadFileResult {
        try {
            contentResolver.query(
                uri,
                null,
                null,
                null,
                null
            )!!.use { cursor ->
                if (cursor.count == 0) throw IllegalStateException("File not found")
                cursor.moveToFirst()
                val nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                val fileName = cursor.getString(nameIndex)
                val bytes = contentResolver.openInputStream(uri)!!.use { inputStream ->
                    inputStream.readBytes()
                }
                return ReadFileResult(fileName, bytes)
            }
        } catch (e: Exception) {
            throw IllegalAccessException("Can't read file. error = $e")
        }
    }
}
