package com.imgur.upload

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imgur.base_ui.recycler.OnItemClickListener
import com.imgur.core_api.extensions.MutableSafeLiveData
import com.imgur.network_api.extension.Response
import com.imgur.upload.entity.AccountItemEntity
import com.imgur.upload.repository.AccountRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class UploadViewModel @Inject constructor(
    private val repository: AccountRepository
) : ViewModel(), OnItemClickListener<AccountItemEntity> {

    val swipeIsRefreshing = MutableSafeLiveData(false)

    val items = MutableSafeLiveData<List<AccountItemEntity>>(emptyList())

    init {
        refresh()
    }

    fun refresh() {
        viewModelScope.launch(Dispatchers.IO) {
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
        viewModelScope.launch(Dispatchers.IO) {
            swipeIsRefreshing.postValue(true)

            when (repository.uploadImage(uri)) {
                is Response.Success -> {
                    refresh()
                }

                else -> {
                    //todo сщщбщение об ошибке

                    swipeIsRefreshing.postValue(false)
                }
            }
        }
    }

    override fun onItemClick(position: Int, model: AccountItemEntity, resId: Int) {
        Timber.d("$model")
    }
}
