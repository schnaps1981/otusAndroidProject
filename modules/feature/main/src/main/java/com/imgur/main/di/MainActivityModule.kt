package com.imgur.main.di

import androidx.lifecycle.ViewModelProvider
import com.imgur.main.MainViewModelFactory
import dagger.Binds
import dagger.Module

@Module
interface MainActivityModule {

    @Binds
    fun bindsMainViewModelFactory(mainViewModelFactory: MainViewModelFactory): ViewModelProvider.Factory
}