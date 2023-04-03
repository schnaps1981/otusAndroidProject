package com.imgur.favorites.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.imgur.core_api.scope.FragmentScope
import com.imgur.core_api.viewmodel.ViewModelFactory
import com.imgur.core_api.viewmodel.ViewModelKey
import com.imgur.favorites.FavoritesViewModel
import com.imgur.favorites.list.FavoriteItemAdapter
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
interface FavoritesFragmentModule {

    @Binds
    @IntoMap
    @ViewModelKey(FavoritesViewModel::class)
    fun bindSearchViewModelFactory(viewModel: FavoritesViewModel): ViewModel

    companion object {

        @Provides
        @FragmentScope
        fun provideSearchItemAdapterAdapter(
            factory: ViewModelFactory,
            vmStore: ViewModelStoreOwner
        ): FavoriteItemAdapter {
            val viewModel = ViewModelProvider(vmStore, factory)[FavoritesViewModel::class.java]

            return FavoriteItemAdapter(viewModel)
        }
    }
}
