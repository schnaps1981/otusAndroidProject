package com.imgur.upload

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class UploadViewModel: ViewModel() {



}

@Suppress("UNCHECKED_CAST")
class UploadViewModelFactory @Inject constructor(
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UploadViewModel() as T
    }
}