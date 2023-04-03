package com.imgur.login.di

import androidx.lifecycle.ViewModel
import com.imgur.core_api.viewmodel.ViewModelKey
import com.imgur.login.LoginViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface LoginFragmentModule {

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    fun bindLoginViewModelFactory(viewModel: LoginViewModel): ViewModel
}
