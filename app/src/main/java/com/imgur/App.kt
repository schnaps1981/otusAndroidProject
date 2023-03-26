package com.imgur

import android.app.Application
import com.imgur.core_api.RootProvider
import com.imgur.core_api.AppRootProvider
import com.imgur.di.RootProviderComponent
import timber.log.Timber

class App : Application(), AppRootProvider {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())

        getRootProvider()
    }

    override fun getRootProvider(): RootProvider {
        return rootComponent ?: RootProviderComponent.init(this).also {
            rootComponent = it
        }
    }

    companion object {

        private var rootComponent: RootProviderComponent? = null
    }
}
