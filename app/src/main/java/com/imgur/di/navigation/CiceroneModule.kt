package com.imgur.di.navigation

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
object CiceroneModule {

    private val cicerone: Cicerone<Router> = Cicerone.create()
    private val overlayCicerone: Cicerone<Router> = Cicerone.create()

    @Provides
    @Singleton
    @Named("bottom")
    fun provideRouter(): Router = cicerone.router

    @Provides
    @Singleton
    @Named("bottom")
    fun provideNavigationHolder(): NavigatorHolder = cicerone.getNavigatorHolder()

    @Provides
    @Singleton
    @Named("overlay")
    fun provideOverlayRouter(): Router = overlayCicerone.router

    @Provides
    @Singleton
    @Named("overlay")
    fun provideOverlayNavigationHolder(): NavigatorHolder = overlayCicerone.getNavigatorHolder()
}
