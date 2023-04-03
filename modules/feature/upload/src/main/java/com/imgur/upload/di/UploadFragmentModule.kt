package com.imgur.upload.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.imgur.core_api.scope.FragmentScope
import com.imgur.core_api.viewmodel.ViewModelFactory
import com.imgur.core_api.viewmodel.ViewModelKey
import com.imgur.upload.UploadViewModel
import com.imgur.upload.list.AccountImagesItemAdapter
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
interface UploadFragmentModule {

    @Binds
    @IntoMap
    @ViewModelKey(UploadViewModel::class)
    fun bindSearchViewModelFactory(viewModel: UploadViewModel): ViewModel


    companion object {

        @Provides
        @FragmentScope
        fun provideSearchItemAdapterAdapter(
            factory: ViewModelFactory,
            vmStore: ViewModelStoreOwner
        ): AccountImagesItemAdapter {
            val viewModel = ViewModelProvider(vmStore, factory)[UploadViewModel::class.java]

            return AccountImagesItemAdapter(viewModel)
        }
    }
}
