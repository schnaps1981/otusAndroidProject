package com.imgur.database_factory

import android.content.Context
import com.imgur.database_api.DatabaseProvider
import com.imgur.database_impl.di.DaggerDatabaseComponent


object DatabaseProvidersFactory {
    fun createDatabaseBuilder(context: Context): DatabaseProvider {
        return DaggerDatabaseComponent.factory().create(context)
    }
}
