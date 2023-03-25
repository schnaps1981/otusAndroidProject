package com.imgur.core_api

import com.github.terrakok.cicerone.NavigatorHolder

interface NavigationProvider {

    fun provideNavigationHolder(): NavigatorHolder

    fun provideBottomNavigation(): BottomNavRouter
}
