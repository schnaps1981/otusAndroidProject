package com.imgur.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imgur.base.extensions.MutableSafeLiveData
import com.imgur.base.recycler.OnItemClickListener
import com.imgur.core_api.dispatchers.IoDispatcher
import com.imgur.core_api.dispatchers.MainDispatcher
import com.imgur.core_api.tools.SnackBarProducer
import com.imgur.core_api.viewmodel.TestOpen
import com.imgur.favorites.entity.FavoriteEntity
import com.imgur.favorites.repository.FavoriteRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@TestOpen
class FavoritesViewModel @Inject constructor(
    private val repository: FavoriteRepository,
    private val snackBarProducer: SnackBarProducer,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    @MainDispatcher private val mainDispatcher: CoroutineDispatcher
) : ViewModel(),
    OnItemClickListener<FavoriteEntity> {

    val swipeIsRefreshing = MutableSafeLiveData(false)

    val favoriteList = MutableSafeLiveData(emptyList<FavoriteEntity>())

    init {
        refresh()
    }

    private fun refresh() {
        swipeIsRefreshing.value = true

        viewModelScope.launch(ioDispatcher) {
            val list = repository.getFavorites()

            favoriteList.postValue(list)

            swipeIsRefreshing.postValue(false)
        }
    }

    fun onRefresh() {
        refresh()
    }

    override fun onItemClick(position: Int, model: FavoriteEntity, resId: Int) {
        viewModelScope.launch(ioDispatcher) {
            val countDeleted = repository.deleteById(model.id)

            if (countDeleted > 0) {
                withContext(mainDispatcher) {
                    refresh()
                }
            } else {
                snackBarProducer.messageStringId.postValue(R.string.favorites_error_delete)
            }
        }
    }
}
