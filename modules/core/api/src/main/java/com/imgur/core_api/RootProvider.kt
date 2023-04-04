package com.imgur.core_api

import com.imgur.core_api.datastore.UserPreferencesProvider
import com.imgur.core_api.navigation.NavigationProvider

interface RootProvider : AppProvider, NavigationProvider, UserPreferencesProvider
