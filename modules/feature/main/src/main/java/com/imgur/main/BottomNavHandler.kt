package com.imgur.main

import androidx.annotation.IdRes
import androidx.databinding.BindingAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView

interface BottomNavHandler {

    fun onNavigationClick(@IdRes item: Int)

    fun onNavigationReselectClick(@IdRes item: Int)
}

object BottomNavigationBinding {

    @JvmStatic
    @BindingAdapter("onNavigationItemSelected")
    fun onNavigationItemSelected(view: BottomNavigationView, handler: BottomNavHandler) {
        view.setOnItemSelectedListener {
            handler.onNavigationClick(it.itemId)
            true
        }

        view.setOnItemReselectedListener {
            handler.onNavigationReselectClick(it.itemId)
        }
    }
}
