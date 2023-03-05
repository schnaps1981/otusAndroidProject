package com.imgur.upload.di

import androidx.lifecycle.ViewModelProvider
import com.imgur.upload.UploadViewModelFactory
import dagger.Binds
import dagger.Module

@Module
interface UploadFragmentModule {

    @Binds
    fun bindUploadViewModelFactory(searchViewModelFactory: UploadViewModelFactory): ViewModelProvider.Factory
}