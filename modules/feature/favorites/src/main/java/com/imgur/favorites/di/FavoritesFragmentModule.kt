package com.imgur.favorites.di

import androidx.lifecycle.ViewModel
import com.imgur.core_api.viewmodel.ViewModelKey
import com.imgur.favorites.FavoritesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface FavoritesFragmentModule {

    @Binds
    @IntoMap
    @ViewModelKey(FavoritesViewModel::class)
    fun bindSearchViewModelFactory(viewModel: FavoritesViewModel): ViewModel
}
