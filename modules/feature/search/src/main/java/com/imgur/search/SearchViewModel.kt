package com.imgur.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class SearchViewModel: ViewModel() {



}

@Suppress("UNCHECKED_CAST")
class SearchViewModelFactory @Inject constructor(
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SearchViewModel() as T
    }
}