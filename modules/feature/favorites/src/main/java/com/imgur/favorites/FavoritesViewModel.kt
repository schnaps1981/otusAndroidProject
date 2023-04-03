package com.imgur.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imgur.base_ui.recycler.OnItemClickListener
import com.imgur.base_ui.recycler.PagedInteraction
import com.imgur.core_api.extensions.MutableSafeLiveData
import com.imgur.favorites.entity.FavoriteEntity
import com.imgur.favorites.repository.FavoriteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoritesViewModel @Inject constructor(
    private val repository: FavoriteRepository
) : ViewModel(),
    OnItemClickListener<FavoriteEntity>, PagedInteraction {

    override val prefetchDistance: Int
        get() = 5

    val swipeIsRefreshing = MutableSafeLiveData(false)

    val favoriteList = MutableSafeLiveData(emptyList<FavoriteEntity>())

    init {
        refresh()
    }

    private fun refresh() {
        viewModelScope.launch(Dispatchers.IO) {
            val list = repository.getFavorites()

            favoriteList.postValue(list)
        }
    }

    fun onRefresh() {

    }

    override fun onFetchNext(): Boolean {
        return true
    }

    override fun onItemClick(position: Int, model: FavoriteEntity, resId: Int) {

    }
}
