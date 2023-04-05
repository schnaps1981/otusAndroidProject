package com.imgur.upload.di

import android.content.Context
import androidx.lifecycle.ViewModelStoreOwner
import com.imgur.core_api.datastore.UserPreferencesProvider
import com.imgur.core_api.scope.FragmentScope
import com.imgur.core_api.viewmodel.ViewModelFactoryModule
import com.imgur.network_api.NetworkProvider
import com.imgur.network_factory.NetworkProvidersFactory
import com.imgur.upload.UploadFragment
import dagger.BindsInstance
import dagger.Component

@FragmentScope
@Component(
    modules = [
        UploadFragmentModule::class,
        UploadRepositoryModule::class,
        ViewModelFactoryModule::class
    ],
    dependencies = [NetworkProvider::class, UserPreferencesProvider::class]
)
interface UploadComponent {

    fun inject(fragment: UploadFragment)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance context: Context,
            @BindsInstance viewModelStoreOwner: ViewModelStoreOwner,
            databaseProvider: NetworkProvider,
            userPreferencesProvider: UserPreferencesProvider
        ): UploadComponent
    }

    companion object {
        fun create(
            context: Context,
            vmStoreOwner: ViewModelStoreOwner,
            userPreferencesProvider: UserPreferencesProvider
        ): UploadComponent {

            val networkProvider =
                NetworkProvidersFactory.createNetworkBuilder(userPreferencesProvider)

            return DaggerUploadComponent
                .factory()
                .create(context, vmStoreOwner, networkProvider, userPreferencesProvider)
        }
    }
}
