package com.imgur.di.navigation.router

import com.github.terrakok.cicerone.Router
import com.imgur.core_api.navigation.OverlayNavRouter
import com.imgur.di.navigation.screens.ScreensFragment
import com.imgur.di.navigation.screens.ScreensOverlay
import javax.inject.Inject
import javax.inject.Named

class OverlayNavRouterImpl @Inject constructor(
    @Named("overlay")
    private val router: Router,
    @Named("bottom")
    private val bottomRouter: Router
) : OverlayNavRouter {
    override fun openLoginScreen() {
        router.navigateTo(ScreensOverlay.openLoginScreen())
    }

    override fun finish() {
        router.finishChain()
    }

    override fun openMainScreen() {
        router.backTo(null)
        bottomRouter.navigateTo(ScreensFragment.openSearchScreen())
    }
}
