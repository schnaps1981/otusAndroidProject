package com.imgur.upload.di

import androidx.lifecycle.ViewModel
import com.imgur.core_api.viewmodel.ViewModelKey
import com.imgur.upload.UploadViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface UploadFragmentModule {

    @Binds
    @IntoMap
    @ViewModelKey(UploadViewModel::class)
    fun bindSearchViewModelFactory(viewModel: UploadViewModel): ViewModel
}