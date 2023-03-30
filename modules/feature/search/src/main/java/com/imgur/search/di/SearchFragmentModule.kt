package com.imgur.search.di

import androidx.lifecycle.ViewModel
import com.imgur.core_api.viewmodel.ViewModelKey
import com.imgur.search.SearchViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface SearchFragmentModule {

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    fun bindSearchViewModelFactory(viewModel: SearchViewModel): ViewModel
}