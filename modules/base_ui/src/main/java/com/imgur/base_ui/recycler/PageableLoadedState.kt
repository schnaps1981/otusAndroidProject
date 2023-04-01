package com.imgur.base_ui.recycler

sealed class PageableLoadedState {
    object Loaded : PageableLoadedState()
    object Loading : PageableLoadedState()
    data class Error(val e: Throwable) : PageableLoadedState()
}
