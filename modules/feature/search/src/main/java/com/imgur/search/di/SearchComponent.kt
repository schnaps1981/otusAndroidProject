package com.imgur.search.di

import android.content.Context
import androidx.lifecycle.ViewModelStoreOwner
import com.imgur.core_api.datastore.UserPreferencesProvider
import com.imgur.core_api.scope.FragmentScope
import com.imgur.core_api.tools.MainToolsProvider
import com.imgur.core_api.viewmodel.ViewModelFactoryModule
import com.imgur.database_api.DatabaseProvider
import com.imgur.database_factory.DatabaseProvidersFactory
import com.imgur.network_api.NetworkProvider
import com.imgur.network_factory.NetworkProvidersFactory
import com.imgur.search.SearchFragment
import dagger.BindsInstance
import dagger.Component

@FragmentScope
@Component(
    dependencies = [
        NetworkProvider::class,
        DatabaseProvider::class,
        UserPreferencesProvider::class,
        MainToolsProvider::class
    ],
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
            networkProvider: NetworkProvider,
            databaseProvider: DatabaseProvider,
            userPrefsProvider: UserPreferencesProvider,
            mainToolsProvider: MainToolsProvider
        ): SearchComponent
    }

    companion object {
        fun create(
            context: Context,
            vmStoreOwner: ViewModelStoreOwner,
            userPrefsProvider: UserPreferencesProvider,
            mainToolsProvider: MainToolsProvider
        ): SearchComponent {

            val networkProvider = NetworkProvidersFactory.createNetworkBuilder(userPrefsProvider)

            val databaseProvider = DatabaseProvidersFactory.createDatabaseBuilder(context)

            return DaggerSearchComponent
                .factory()
                .create(
                    vmStoreOwner,
                    networkProvider,
                    databaseProvider,
                    userPrefsProvider,
                    mainToolsProvider
                )
        }
    }
}
