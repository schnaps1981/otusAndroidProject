package com.imgur.search.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.imgur.core_api.scope.FragmentScope
import com.imgur.core_api.viewmodel.ViewModelFactory
import com.imgur.core_api.viewmodel.ViewModelKey
import com.imgur.search.SearchViewModel
import com.imgur.search.list.SearchItemAdapter
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
interface SearchFragmentModule {

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    fun bindSearchViewModelFactory(viewModel: SearchViewModel): ViewModel

    companion object {

        @Provides
        @FragmentScope
        fun provideSearchItemAdapterAdapter(
            factory: ViewModelFactory,
            vmStore: ViewModelStoreOwner
        ): SearchItemAdapter {
            val viewModel = ViewModelProvider(vmStore, factory)[SearchViewModel::class.java]

            return SearchItemAdapter(viewModel, viewModel)
        }
    }
}
