package com.imgur.favorites.di

import androidx.lifecycle.ViewModelProvider
import com.imgur.favorites.FavoritesViewModelFactory
import dagger.Binds
import dagger.Module

@Module
interface FavoritesFragmentModule {

    @Binds
    fun bindFavoritesViewModelFactory(searchViewModelFactory: FavoritesViewModelFactory): ViewModelProvider.Factory
}