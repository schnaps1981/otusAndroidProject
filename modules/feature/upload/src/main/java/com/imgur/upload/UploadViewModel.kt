package com.imgur.upload

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imgur.base.extensions.MutableSafeLiveData
import com.imgur.base.recycler.OnItemClickListener
import com.imgur.core_api.dispatchers.IoDispatcher
import com.imgur.core_api.tools.SnackBarProducer
import com.imgur.network_api.extension.Response
import com.imgur.upload.entity.AccountItemEntity
import com.imgur.upload.repository.AccountRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class UploadViewModel @Inject constructor(
    private val repository: AccountRepository,
    private val snackBarProducer: SnackBarProducer,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ViewModel(), OnItemClickListener<AccountItemEntity> {

    val swipeIsRefreshing = MutableSafeLiveData(false)

    val items = MutableSafeLiveData<List<AccountItemEntity>>(emptyList())

    init {
        refresh()
    }

    fun refresh() {
        viewModelScope.launch(ioDispatcher) {
            swipeIsRefreshing.postValue(true)

            when (val result = repository.loadAccountImages()) {
                is Response.Success -> {
                    val list = result.value.items.map {
                        AccountItemEntity.valueOf(it)
                    }

                    items.postValue(list)
                    swipeIsRefreshing.postValue(false)
                }

                else -> {
                    //сообщение об ошибке загрузки изображений аккаунта

                    swipeIsRefreshing.postValue(false)
                }
            }
        }
    }

    fun chooseImage(uri: Uri) {
        viewModelScope.launch(ioDispatcher) {
            swipeIsRefreshing.postValue(true)

            when (repository.uploadImage(uri)) {
                is Response.Success -> {
                    refresh()
                }

                else -> {
                    snackBarProducer.messageStringId.postValue(R.string.upload_upload_error)

                    swipeIsRefreshing.postValue(false)
                }
            }
        }
    }

    override fun onItemClick(position: Int, model: AccountItemEntity, resId: Int) {
        viewModelScope.launch {
            swipeIsRefreshing.postValue(true)
            val result = repository.deleteImage(model.deleteHash)

            if (result !is Response.Success) {
                snackBarProducer.messageStringId.postValue(R.string.upload_delete_error)
            } else {
                refresh()
            }
            swipeIsRefreshing.postValue(false)
        }
    }
}
