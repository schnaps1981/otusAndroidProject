package com.imgur.upload.di

import android.content.Context
import androidx.lifecycle.ViewModelStoreOwner
import com.imgur.core_api.viewmodel.ViewModelFactoryModule
import com.imgur.network_api.NetworkProvider
import com.imgur.network_factory.NetworkProvidersFactory
import com.imgur.upload.UploadFragment
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [
        UploadFragmentModule::class,
        UploadRepositoryModule::class,
        ViewModelFactoryModule::class
    ],
    dependencies = [NetworkProvider::class]
)
interface UploadComponent {

    fun inject(fragment: UploadFragment)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance context: Context,
            @BindsInstance viewModelStoreOwner: ViewModelStoreOwner,
            databaseProvider: NetworkProvider
        ): UploadComponent
    }

    companion object {
        fun create(context: Context, vmStoreOwner: ViewModelStoreOwner): UploadComponent {

            val networkProvider = NetworkProvidersFactory.createNetworkBuilder()

            return DaggerUploadComponent
                .factory()
                .create(context, vmStoreOwner, networkProvider)
        }
    }
}
