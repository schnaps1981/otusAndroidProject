package com.imgur.search.di

import androidx.lifecycle.ViewModelStoreOwner
import com.imgur.core_api.scope.FragmentScope
import com.imgur.core_api.viewmodel.ViewModelFactoryModule
import com.imgur.network_api.NetworkProvider
import com.imgur.network_factory.NetworkProvidersFactory
import com.imgur.search.SearchFragment
import dagger.BindsInstance
import dagger.Component

@FragmentScope
@Component(
    dependencies = [NetworkProvider::class],
    modules = [
        SearchFragmentModule::class,
        RepositoryModule::class,
        ViewModelFactoryModule::class
    ]
)
interface SearchComponent {

    fun inject(fragment: SearchFragment)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance viewModelStoreOwner: ViewModelStoreOwner,
            networkProvider: NetworkProvider
        ): SearchComponent
    }

    companion object {
        fun create(vmStoreOwner: ViewModelStoreOwner): SearchComponent {

            val networkProvider = NetworkProvidersFactory.createNetworkBuilder()

            return DaggerSearchComponent
                .factory()
                .create(vmStoreOwner, networkProvider)
        }
    }
}
