package com.imgur.main

import androidx.annotation.IdRes
import androidx.lifecycle.*
import com.imgur.core_api.BottomNavRouter
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val bottomNavRouter: BottomNavRouter
) : ViewModel(), BottomNavHandler, DefaultLifecycleObserver {
    override fun onNavigationReselectClick(@IdRes item: Int) {

    }

    override fun onNavigationClick(@IdRes item: Int) {
        when (item) {
            R.id.nav_search -> {
                bottomNavRouter.openSearchScreen()
            }

            R.id.nav_favorites -> {
                bottomNavRouter.openFavoritesScreen()
            }

            R.id.nav_upload -> {
                bottomNavRouter.openUploadScreen()
            }
        }
    }

    override fun onStart(owner: LifecycleOwner) {
        bottomNavRouter.openSearchScreen()
    }
}

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory @Inject constructor(
    private val bottomNavRouter: BottomNavRouter
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(bottomNavRouter) as T
    }
}
