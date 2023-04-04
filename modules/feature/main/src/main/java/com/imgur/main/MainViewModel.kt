package com.imgur.main

import androidx.annotation.IdRes
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imgur.core_api.datastore.UserPreferences
import com.imgur.core_api.navigation.BottomNavRouter
import com.imgur.core_api.navigation.OverlayNavRouter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val bottomNavRouter: BottomNavRouter,
    private val overlayNavRouter: OverlayNavRouter,
    userPreferences: UserPreferences
) : ViewModel(), BottomNavHandler, DefaultLifecycleObserver {

    private val accessToken = userPreferences.accessToken

    override fun onNavigationReselectClick(@IdRes item: Int) {
        onNavigationClick(item)
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

    override fun onCreate(owner: LifecycleOwner) {
        viewModelScope.launch(Dispatchers.IO) {
            val hasAccessToken = !accessToken.first().isNullOrEmpty()

            if (hasAccessToken) {
                bottomNavRouter.openSearchScreen()
            } else {
                overlayNavRouter.openLoginScreen()
            }
        }
    }
}
