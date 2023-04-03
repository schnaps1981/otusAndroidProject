package com.imgur.base.recycler

import androidx.annotation.IdRes

interface OnItemClickListener<D> {

    fun onItemClick(position: Int, model: D, @IdRes resId: Int)

    fun onItemLongClick(position: Int, model: D, @IdRes resId: Int) {}
}
