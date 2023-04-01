package com.imgur.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imgur.base_ui.recycler.OnItemClickListener
import com.imgur.base_ui.recycler.PagedInteraction
import com.imgur.core_api.extensions.MutableSafeLiveData
import com.imgur.core_api.extensions.debounce
import com.imgur.database_api.dto.FavoriteItem
import com.imgur.network_api.extension.Response
import com.imgur.search.entity.SearchItemEntity
import com.imgur.search.repository.SearchRepository
import kotlinx.coroutines.*
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val repository: SearchRepository
) : ViewModel(), OnItemClickListener<SearchItemEntity>, PagedInteraction {

    override val prefetchDistance: Int
        get() = 5

    private var currentPage = 0
    private var hasNextPage = true

    private val job = SupervisorJob()
    private val scope = CoroutineScope(Dispatchers.IO + job)

    val swipeIsRefreshing = MutableSafeLiveData(false)

    val searchResult = MutableSafeLiveData<List<SearchItemEntity>>(emptyList())
    val searchText = MutableSafeLiveData("")

    init {

        searchText.debounce(INPUT_TEXT_DEBOUNCE_MS, viewModelScope).observeForever {
            if (it.isBlank()) {
                searchResult.value = emptyList()
            } else {
                refreshAndSearch()
            }
        }
    }

    fun refreshAndSearch() {
        currentPage = 0
        hasNextPage = true

        fetchNextPage()
    }

    private fun fetchNextPage(isNextPage: Boolean = false) {
        if (searchText.value.isBlank() || searchText.value.length < MIN_SYMBOLS_FOR_SEARCH) {
            return
        }

        swipeIsRefreshing.value = true

        job.cancelChildren()

        scope.launch {
            val result = repository.searchByQuery(pageNum = currentPage, query = searchText.value)

            if (this.isActive) {
                val updated: MutableList<SearchItemEntity> = mutableListOf()

                when (result) {
                    is Response.Success -> {

                        val contentItems =
                            result.value.data.map {
                                SearchItemEntity.valueOf(it)
                            }

                        val items =
                            if (isNextPage) searchResult.value + contentItems else contentItems

                        updated.addAll(items)

                        hasNextPage = contentItems.isNotEmpty()
                        currentPage++

                        searchResult.postValue(updated)
                        swipeIsRefreshing.postValue(false)
                    }

                    else -> {
                        //todo сщщбщение об ошибке
                        swipeIsRefreshing.postValue(false)
                    }
                }
            }
        }
    }

    override fun onFetchNext(): Boolean {
        if (hasNextPage && !swipeIsRefreshing.value) {
            fetchNextPage(true)
        }

        return hasNextPage
    }


    override fun onCleared() {
        scope.cancel()
        super.onCleared()
    }

    override fun onItemClick(position: Int, model: SearchItemEntity, resId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val favoriteItem = FavoriteItem(
                imageId = model.id,
                imageUrl = model.imageUrl,
                title = model.title
            )

            val addedItems = repository.addToFavorite(favoriteItem)

            if (addedItems == 0L) {
                //todo сообщение об ощибке добавления в избранное
            }
        }
    }

    companion object {
        const val MIN_SYMBOLS_FOR_SEARCH = 3
        const val INPUT_TEXT_DEBOUNCE_MS = 1000L
    }
}
