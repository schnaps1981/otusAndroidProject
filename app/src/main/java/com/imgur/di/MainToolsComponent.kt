package com.imgur.di

import android.content.Context
import com.imgur.core_api.tools.MainToolsProvider
import com.imgur.core_api.tools.SnackBarConsumer
import com.imgur.core_api.tools.SnackBarProducer
import com.imgur.di.events.SnackBarObserver
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object MainToolsModule {
    @Provides
    @Singleton
    fun provideSnackBarObserver() = SnackBarObserver()

    @Provides
    @Singleton
    fun provideSnackBarConsumer(observer: SnackBarObserver): SnackBarConsumer = observer

    @Provides
    @Singleton
    fun provideSnackBarProducer(observer: SnackBarObserver): SnackBarProducer = observer
}

@Singleton
@Component(modules = [MainToolsModule::class])
interface MainToolsComponent : MainToolsProvider {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: Context): MainToolsProvider
    }

    companion object {
        fun create(app: Context): MainToolsProvider {
            return DaggerMainToolsComponent.factory().create(app)
        }
    }
}
