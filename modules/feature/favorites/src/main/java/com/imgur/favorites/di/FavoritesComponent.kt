package com.imgur.favorites.di

import com.imgur.favorites.FavoritesFragment
import dagger.Component

@Component(
    modules = [FavoritesFragmentModule::class]
)
interface FavoritesComponent {

    fun inject(fragment: FavoritesFragment)

    companion object {
        fun create(): FavoritesComponent {
            return DaggerFavoritesComponent.builder().build()
        }
    }
}