package com.imgur.base_ui.recycler

import android.view.View

interface OnItemClickListenerBinding {

    fun onClickItem(view: View)

    fun onLongClickItem(view: View?): Boolean = true
}
