package com.imgur.di.navigation

import com.github.terrakok.cicerone.Router
import com.imgur.core_api.BottomNavRouter
import javax.inject.Inject

class BottomNavRouterImpl @Inject constructor(
    private val router: Router
) : BottomNavRouter {
    override fun openSearchScreen() {
        router.navigateTo(Screens.openSearchScreen())
    }

    override fun openFavoritesScreen() {
        router.navigateTo(Screens.openFavoritesScreen())
    }

    override fun openUploadScreen() {
        router.navigateTo(Screens.openUploadScreen())
    }
}
