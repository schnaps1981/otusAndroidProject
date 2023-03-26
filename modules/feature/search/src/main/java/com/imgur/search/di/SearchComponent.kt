package com.imgur.search.di

import com.imgur.network_api.NetworkProvider
import com.imgur.network_factory.NetworkProvidersFactory
import com.imgur.search.SearchFragment
import dagger.Component

@Component(
    dependencies = [NetworkProvider::class],
    modules = [SearchFragmentModule::class]
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