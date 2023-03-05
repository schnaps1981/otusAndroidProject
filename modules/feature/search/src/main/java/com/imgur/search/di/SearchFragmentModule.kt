package com.imgur.search.di

import androidx.lifecycle.ViewModelProvider
import com.imgur.search.SearchViewModelFactory
import dagger.Binds
import dagger.Module

@Module
interface SearchFragmentModule {

    @Binds
    fun bindSearchViewModelFactory(searchViewModelFactory: SearchViewModelFactory): ViewModelProvider.Factory
}