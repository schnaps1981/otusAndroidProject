package com.imgur.base.binding

import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

object BindingAdapters {
    @BindingAdapter("onSwipeRefresh")
    @JvmStatic
    fun onSwipeRefresh(view: SwipeRefreshLayout, callback: () -> Unit) {
        view.setOnRefreshListener { callback() }
    }

    @BindingAdapter("isSwipeRefreshing")
    @JvmStatic
    fun isSwipeRefreshing(view: SwipeRefreshLayout, isRefreshing: Boolean) {
        view.isRefreshing = isRefreshing
    }
}
