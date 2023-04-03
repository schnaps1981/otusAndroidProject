package com.imgur.di.navigation

import com.imgur.core_api.navigation.BottomNavRouter
import com.imgur.core_api.navigation.OverlayNavRouter
import com.imgur.di.navigation.router.BottomNavRouterImpl
import com.imgur.di.navigation.router.OverlayNavRouterImpl
import dagger.Binds
import dagger.Module

@Module
interface NavModule {
    @Binds
    fun bindBottomNavigation(nav: BottomNavRouterImpl): BottomNavRouter

    @Binds
    fun bindOverlayNavigation(nav: OverlayNavRouterImpl): OverlayNavRouter
}
