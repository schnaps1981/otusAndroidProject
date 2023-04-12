package com.imgur.core_api

import com.imgur.core_api.datastore.UserPreferencesProvider
import com.imgur.core_api.navigation.NavigationProvider
import com.imgur.core_api.tools.MainToolsProvider

interface RootProvider : AppProvider, NavigationProvider, UserPreferencesProvider, MainToolsProvider
