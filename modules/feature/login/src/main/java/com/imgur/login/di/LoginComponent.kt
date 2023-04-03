package com.imgur.login.di

import com.imgur.core_api.RootProvider
import com.imgur.core_api.scope.FragmentScope
import com.imgur.core_api.viewmodel.ViewModelFactoryModule
import com.imgur.login.LoginFragment
import dagger.Component

@FragmentScope
@Component(
    dependencies = [RootProvider::class],
    modules = [LoginFragmentModule::class, ViewModelFactoryModule::class]

)
interface LoginComponent {

    fun inject(fragment: LoginFragment)

    @Component.Factory
    interface Factory {
        fun create(rootProvider: RootProvider): LoginComponent
    }

    companion object {

        fun create(rootProvider: RootProvider): LoginComponent {
            return DaggerLoginComponent.factory().create(rootProvider)
        }
    }
}
