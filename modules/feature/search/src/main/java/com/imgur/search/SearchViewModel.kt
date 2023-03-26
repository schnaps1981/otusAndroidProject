package com.imgur.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.imgur.core_api.extensions.MutableSafeLiveData
import com.imgur.network_api.extension.Response
import com.imgur.network_api.models.AccountImages
import com.imgur.search.repository.ImgurAccountRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel(private val repository: ImgurAccountRepository) : ViewModel() {

    val data = MutableSafeLiveData(AccountImages())

    init {
        viewModelScope.launch {
            when (val data = repository.getAccountImages()) {
                is Response.Success -> {
                    this@SearchViewModel.data.postValue(data.value)
                }

                else -> {}
            }
        }
    }
}

@Suppress("UNCHECKED_CAST")
class SearchViewModelFactory @Inject constructor(
    private val repository: ImgurAccountRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SearchViewModel(repository) as T
    }
}
