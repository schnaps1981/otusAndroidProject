package com.imgur.search.di

import com.imgur.search.SearchFragment
import dagger.Component

@Component(
    modules = [SearchFragmentModule::class]
)
interface SearchComponent {

    fun inject(fragment: SearchFragment)

    companion object {
        fun create(): SearchComponent {
            return DaggerSearchComponent.builder().build()
        }
    }
}