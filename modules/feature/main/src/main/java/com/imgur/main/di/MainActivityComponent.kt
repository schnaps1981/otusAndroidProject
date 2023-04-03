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

    @Component.Factory
    interface Factory {
        fun create(rootProvider: RootProvider): MainActivityComponent
    }

    companion object {
        fun create(rootProvider: RootProvider): MainActivityComponent {
            return DaggerMainActivityComponent.factory().create(rootProvider)
        }
    }
}
