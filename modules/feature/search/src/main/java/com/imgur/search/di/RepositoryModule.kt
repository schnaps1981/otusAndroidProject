package com.imgur.search.di

import com.imgur.search.repository.SearchRepository
import com.imgur.search.repository.SearchRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {

    @Binds
    fun bindImgurRepository(repositoryImpl: SearchRepositoryImpl): SearchRepository
}
