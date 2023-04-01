package com.imgur.favorites.di

import com.imgur.favorites.repository.FavoriteRepository
import com.imgur.favorites.repository.FavoriteRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {
    @Binds
    fun bindImgurRepository(repositoryImpl: FavoriteRepositoryImpl): FavoriteRepository
}