package com.imgur.core_impl.di

import com.imgur.core_api.datastore.UserPreferences
import com.imgur.core_impl.datastore.ISecretKey
import com.imgur.core_impl.datastore.SecretKey
import com.imgur.core_impl.datastore.UserPreferencesImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object SecretKeyModule {
    @Singleton
    @Provides
    fun provideISecretKey(): ISecretKey {
        return SecretKey()
    }
}

@Module
interface UserPreferencesModule {

    @Binds
    fun provideUserPreferences(prefs: UserPreferencesImpl): UserPreferences
}
