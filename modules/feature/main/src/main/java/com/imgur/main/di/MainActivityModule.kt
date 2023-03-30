package com.imgur.main.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.imgur.core_api.scope.ActivityScope
import com.imgur.core_api.viewmodel.ViewModelKey
import com.imgur.main.MainViewModel
import com.imgur.main.MainViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface MainActivityModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindSearchViewModelFactory(viewModel: MainViewModel): ViewModel
}