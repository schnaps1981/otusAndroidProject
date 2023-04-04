package com.imgur.base.binding

import android.view.View
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

    @BindingAdapter("visible")
    @JvmStatic
    fun visible(view: View, visible: Boolean) {
        view.visibility = if (visible) View.VISIBLE else View.GONE
    }
}
