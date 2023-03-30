package com.imgur.search.di

import com.imgur.core_api.scope.FragmentScope
import com.imgur.core_api.viewmodel.ViewModelFactory
import com.imgur.core_api.viewmodel.ViewModelFactoryModule
import com.imgur.network_api.NetworkProvider
import com.imgur.network_factory.NetworkProvidersFactory
import com.imgur.search.SearchFragment
import dagger.Component

@FragmentScope
@Component(
    dependencies = [NetworkProvider::class],
    modules = [SearchFragmentModule::class, RepositoryModule::class, ViewModelFactoryModule::class]
)
interface SearchComponent {

    fun inject(fragment: SearchFragment)

    companion object {
        fun create(): SearchComponent {

            val networkProvider = NetworkProvidersFactory.createNetworkBuilder()

            return DaggerSearchComponent.builder()
                .networkProvider(networkProvider)
                .build()
        }
    }
}
