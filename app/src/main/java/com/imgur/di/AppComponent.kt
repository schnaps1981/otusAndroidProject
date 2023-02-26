package com.imgur.di

import android.app.Application
import android.content.Context
import com.imgur.core_api.AppProvider
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [
        AppModule::class
    ]
)
interface AppComponent: AppProvider {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    companion object {

        private var appComponent: AppProvider? = null

        fun create(application: Application): AppProvider {
            return appComponent ?: DaggerAppComponent
                .factory().create(application.applicationContext)
                .also {
                    appComponent = it
                }
        }
    }
}