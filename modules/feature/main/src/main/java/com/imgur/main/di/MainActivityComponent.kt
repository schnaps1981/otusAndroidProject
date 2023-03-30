package com.imgur.main.di


import com.imgur.core_api.RootProvider
import com.imgur.core_api.scope.ActivityScope
import com.imgur.core_api.viewmodel.ViewModelFactoryModule
import com.imgur.main.MainActivity
import dagger.Component

@ActivityScope
@Component(
    dependencies = [RootProvider::class],
    modules = [MainActivityModule::class, ViewModelFactoryModule::class]
)
interface MainActivityComponent {

    fun inject(activity: MainActivity)

    companion object {
        fun create(rootProvider: RootProvider): MainActivityComponent {
            return DaggerMainActivityComponent.builder().rootProvider(rootProvider).build()
        }
    }
}
