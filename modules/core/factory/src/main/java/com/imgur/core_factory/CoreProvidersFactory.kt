package com.imgur.core_factory

import com.imgur.core_api.AppProvider
import com.imgur.core_api.datastore.UserPreferencesProvider
import com.imgur.core_impl.di.DaggerUserPreferencesComponent

object CoreProvidersFactory {

    fun createUserPreferencesProvider(appProvider: AppProvider): UserPreferencesProvider {
        return DaggerUserPreferencesComponent.factory().create(appProvider)
    }
}
