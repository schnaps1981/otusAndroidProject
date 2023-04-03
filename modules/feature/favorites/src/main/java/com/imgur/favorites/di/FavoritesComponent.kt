package com.imgur.favorites.di

import android.content.Context
import androidx.lifecycle.ViewModelStoreOwner
import com.imgur.core_api.scope.FragmentScope
import com.imgur.core_api.viewmodel.ViewModelFactoryModule
import com.imgur.database_api.DatabaseProvider
import com.imgur.database_factory.DatabaseProvidersFactory
import com.imgur.favorites.FavoritesFragment
import dagger.BindsInstance
import dagger.Component

@FragmentScope
@Component(
    dependencies = [DatabaseProvider::class],
    modules = [FavoritesFragmentModule::class, ViewModelFactoryModule::class, RepositoryModule::class]
)
interface FavoritesComponent {

    fun inject(fragment: FavoritesFragment)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance viewModelStoreOwner: ViewModelStoreOwner,
            databaseProvider: DatabaseProvider
        ): FavoritesComponent
    }

    companion object {

        fun create(context: Context, vmStoreOwner: ViewModelStoreOwner): FavoritesComponent {

            val databaseProvider = DatabaseProvidersFactory.createDatabaseBuilder(context)

            return DaggerFavoritesComponent.factory().create(vmStoreOwner, databaseProvider)
        }
    }
}
