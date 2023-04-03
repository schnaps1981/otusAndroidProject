package com.imgur.upload.di

import com.imgur.upload.repository.UploadRepository
import com.imgur.upload.repository.UploadRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
interface UploadRepositoryModule {

    @Binds
    fun bindUploadRepository(repositoryImpl: UploadRepositoryImpl): UploadRepository
}
