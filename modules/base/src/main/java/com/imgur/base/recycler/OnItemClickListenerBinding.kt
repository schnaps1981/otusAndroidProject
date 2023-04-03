package com.imgur.base.recycler

import android.view.View

interface OnItemClickListenerBinding {

    fun onClickItem(view: View)

    fun onLongClickItem(view: View?): Boolean = true
}
