package com.imgur.upload

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imgur.network_api.extension.Response
import com.imgur.upload.repository.UploadRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class UploadViewModel @Inject constructor(
    private val repository: UploadRepository
) : ViewModel() {

    fun chooseImage(uri: Uri) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.uploadImage(uri)

            when (result) {
                is Response.Success -> {
                    //сообщение, что загрузка успешна
                }

                else -> {
                    //todo сщщбщение об ошибке

                    //индикатор загрузки = false
                }
            }
        }
    }
}
