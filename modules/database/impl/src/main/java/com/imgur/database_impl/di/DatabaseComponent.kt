package com.imgur.database_impl.di

import android.content.Context
import com.imgur.database_api.DatabaseProvider
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [DatabaseModule::class]
)
interface DatabaseComponent : DatabaseProvider {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): DatabaseProvider
    }
}
