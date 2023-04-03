package com.imgur.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imgur.base.recycler.OnItemClickListener
import com.imgur.base.extensions.MutableSafeLiveData
import com.imgur.favorites.entity.FavoriteEntity
import com.imgur.favorites.repository.FavoriteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoritesViewModel @Inject constructor(
    private val repository: FavoriteRepository
) : ViewModel(),
    OnItemClickListener<FavoriteEntity> {

    val swipeIsRefreshing = MutableSafeLiveData(false)

    val favoriteList = MutableSafeLiveData(emptyList<FavoriteEntity>())

    init {
        refresh()
    }

    private fun refresh() {
        swipeIsRefreshing.value = true

        viewModelScope.launch(Dispatchers.IO) {
            val list = repository.getFavorites()

            favoriteList.postValue(list)

            swipeIsRefreshing.postValue(false)
        }
    }

    fun onRefresh() {
        refresh()
    }

    override fun onItemClick(position: Int, model: FavoriteEntity, resId: Int) {

    }
}
