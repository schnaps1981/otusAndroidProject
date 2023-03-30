package com.imgur.favorites.di

import com.imgur.core_api.viewmodel.ViewModelFactoryModule
import com.imgur.favorites.FavoritesFragment
import dagger.Component

@Component(
    modules = [FavoritesFragmentModule::class, ViewModelFactoryModule::class]
)
interface FavoritesComponent {

    fun inject(fragment: FavoritesFragment)

    companion object {
        fun create(): FavoritesComponent {
            return DaggerFavoritesComponent.builder().build()
        }
    }
}