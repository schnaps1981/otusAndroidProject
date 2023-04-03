package com.imgur.base.recycler

sealed class PageableLoadedState {
    object Loaded : PageableLoadedState()
    object Loading : PageableLoadedState()
    data class Error(val e: Throwable) : PageableLoadedState()
}
