package com.imgur.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class MainViewModel : ViewModel() {
}

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory @Inject constructor(
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel() as T
    }
}