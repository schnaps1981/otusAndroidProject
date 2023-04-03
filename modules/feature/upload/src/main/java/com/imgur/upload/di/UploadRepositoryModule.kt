package com.imgur.upload.di

import com.imgur.upload.repository.AccountRepository
import com.imgur.upload.repository.AccountRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
interface UploadRepositoryModule {

    @Binds
    fun bindUploadRepository(repositoryImpl: AccountRepositoryImpl): AccountRepository
}
