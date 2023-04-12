package com.imgur.main

import androidx.annotation.IdRes
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imgur.base.extensions.MutableSafeLiveData
import com.imgur.core_api.datastore.UserPreferences
import com.imgur.core_api.navigation.BottomNavRouter
import com.imgur.core_api.navigation.OverlayNavRouter
import com.imgur.core_api.tools.SnackBarConsumer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val bottomNavRouter: BottomNavRouter,
    private val overlayNavRouter: OverlayNavRouter,
    private val userPreferences: UserPreferences,
    snackBarConsumer: SnackBarConsumer
) : ViewModel(), BottomNavHandler, DefaultLifecycleObserver {

    private val accessToken: String
        get() = runBlocking { userPreferences.accessToken.first() ?: "" }

    val snackBarMessage = MutableSafeLiveData(0)

    init {
        snackBarConsumer.getMessageStringId().observeForever {
            snackBarMessage.value = it
        }
    }

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

            if (accessToken.isNotEmpty()) {
                bottomNavRouter.openSearchScreen()
            } else {
                overlayNavRouter.openLoginScreen()
            }
        }
    }
}
