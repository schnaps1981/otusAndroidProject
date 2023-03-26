package com.imgur.search.di

import com.imgur.search.repository.ImgurAccountRepository
import com.imgur.search.repository.ImgurAccountRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {

    @Binds
    fun bindImgurRepository(repositoryImpl: ImgurAccountRepositoryImpl): ImgurAccountRepository
}
