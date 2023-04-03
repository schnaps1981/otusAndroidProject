package com.imgur.login.di

import com.imgur.core_api.scope.FragmentScope
import com.imgur.core_api.viewmodel.ViewModelFactoryModule
import com.imgur.login.LoginFragment
import dagger.Component

@FragmentScope
@Component(
    modules = [LoginFragmentModule::class, ViewModelFactoryModule::class]

)
interface LoginComponent {

    fun inject(fragment: LoginFragment)

    @Component.Factory
    interface Factory {
        fun create(): LoginComponent
    }

    companion object {

        fun create(): LoginComponent {
            return DaggerLoginComponent.factory().create()
        }
    }
}