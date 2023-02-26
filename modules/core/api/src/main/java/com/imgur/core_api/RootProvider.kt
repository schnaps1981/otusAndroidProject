package com.imgur.core_api

import com.imgur.database_api.DatabaseProvider
import com.imgur.network_api.NetworkProvider

interface RootProvider : NetworkProvider, AppProvider, DatabaseProvider