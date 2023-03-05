package com.imgur.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class FavoritesViewModel: ViewModel() {



}

@Suppress("UNCHECKED_CAST")
class FavoritesViewModelFactory @Inject constructor(
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FavoritesViewModel() as T
    }
}