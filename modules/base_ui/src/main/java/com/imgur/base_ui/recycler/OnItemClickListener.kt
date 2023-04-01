package com.imgur.base_ui.recycler

import androidx.annotation.IdRes

interface OnItemClickListener<D> {

    fun onItemClick(position: Int, model: D, @IdRes resId: Int)

    fun onItemLongClick(position: Int, model: D, @IdRes resId: Int) {}
}
