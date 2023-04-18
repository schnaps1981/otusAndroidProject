package com.imgur.upload.di

import android.content.Context
import androidx.lifecycle.ViewModelStoreOwner
import com.imgur.core_api.datastore.UserPreferencesProvider
import com.imgur.core_api.dispatchers.DispatchersModule
import com.imgur.core_api.scope.FragmentScope
import com.imgur.core_api.tools.MainToolsProvider
import com.imgur.core_api.viewmodel.ViewModelFactoryModule
import com.imgur.network_api.NetworkProvider
import com.imgur.network_factory.NetworkProvidersFactory
import com.imgur.upload.UploadFragment
import dagger.BindsInstance
import dagger.Component

@FragmentScope
@Component(
    dependencies = [
        NetworkProvider::class,
        UserPreferencesProvider::class,
        MainToolsProvider::class
    ],
    modules = [
        UploadFragmentModule::class,
        UploadRepositoryModule::class,
        ViewModelFactoryModule::class,
        DispatchersModule::class
    ]
)
interface UploadComponent {

    fun inject(fragment: UploadFragment)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance context: Context,
            @BindsInstance viewModelStoreOwner: ViewModelStoreOwner,
            databaseProvider: NetworkProvider,
            userPreferencesProvider: UserPreferencesProvider,
            mainToolsProvider: MainToolsProvider
        ): UploadComponent
    }

    companion object {
        fun create(
            context: Context,
            vmStoreOwner: ViewModelStoreOwner,
            userPreferencesProvider: UserPreferencesProvider,
            mainToolsProvider: MainToolsProvider
        ): UploadComponent {

            val networkProvider =
                NetworkProvidersFactory.createNetworkBuilder(userPreferencesProvider)

            return DaggerUploadComponent
                .factory()
                .create(
                    context,
                    vmStoreOwner,
                    networkProvider,
                    userPreferencesProvider,
                    mainToolsProvider
                )
        }
    }
}
