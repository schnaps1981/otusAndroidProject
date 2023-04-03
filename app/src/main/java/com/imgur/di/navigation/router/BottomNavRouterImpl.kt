package com.imgur.di.navigation.router

import com.github.terrakok.cicerone.Router
import com.imgur.core_api.navigation.BottomNavRouter
import com.imgur.di.navigation.screens.ScreensFragment
import javax.inject.Inject
import javax.inject.Named

class BottomNavRouterImpl @Inject constructor(
    @Named("bottom")
    private val router: Router
) : BottomNavRouter {
    override fun openSearchScreen() {
        router.navigateTo(ScreensFragment.openSearchScreen())
    }

    override fun openFavoritesScreen() {
        router.navigateTo(ScreensFragment.openFavoritesScreen())
    }

    override fun openUploadScreen() {
        router.navigateTo(ScreensFragment.openUploadScreen())
    }
}
