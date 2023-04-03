package com.imgur.base.recycler

import android.view.ViewGroup

//todo может быть не нужен
interface ItemBindingData<D, T> {
    val viewType: Int

    fun createHolder(parent: ViewGroup): AbstractHolder<D>
    fun isCurrentViewType(value: T): Boolean
}
