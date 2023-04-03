package com.imgur.core_api.navigation

import com.github.terrakok.cicerone.NavigatorHolder
import javax.inject.Named

interface NavigationProvider {

    @Named("bottom")
    fun provideBottomNavigationHolder(): NavigatorHolder

    fun provideBottomNavigation(): BottomNavRouter

    @Named("overlay")
    fun provideOverlayNavigationHolder(): NavigatorHolder

    fun provideOverlayNavigation(): OverlayNavRouter
}
