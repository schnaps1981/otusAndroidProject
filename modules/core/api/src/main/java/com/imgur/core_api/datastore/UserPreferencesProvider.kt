package com.imgur.core_api.datastore

interface UserPreferencesProvider {
    fun provideUserPreferences(): UserPreferences
}
