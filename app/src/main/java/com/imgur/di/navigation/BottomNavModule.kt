package com.imgur.di.navigation

import com.imgur.core_api.BottomNavRouter
import dagger.Binds
import dagger.Module

@Module
interface BottomNavModule {
    @Binds
    fun bindBottomNavigation(nav: BottomNavRouterImpl): BottomNavRouter
}
