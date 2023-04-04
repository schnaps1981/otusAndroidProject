package com.imgur.core_impl.di

import com.imgur.core_api.AppProvider
import com.imgur.core_api.datastore.UserPreferencesProvider
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    dependencies = [AppProvider::class],
    modules = [
        SecretKeyModule::class,
        UserPreferencesModule::class
    ]
)
interface UserPreferencesComponent : UserPreferencesProvider {

    @Component.Factory
    interface Factory {
        fun create(appProvider: AppProvider): UserPreferencesProvider
    }
}
