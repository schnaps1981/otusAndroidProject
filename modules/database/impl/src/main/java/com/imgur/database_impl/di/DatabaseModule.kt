package com.imgur.database_impl.di

import android.content.Context
import androidx.room.Room
import com.imgur.database_api.DatabaseContract
import com.imgur.database_api.DatabaseDao
import com.imgur.database_impl.TestDatabase
import dagger.Module
import dagger.Provides
import dagger.Reusable
import javax.inject.Singleton

@Module
object DatabaseModule {

    private const val DATABASE_NAME = "TEST_DB"

    @Provides
    @Reusable
    fun provideTestDao(contract: DatabaseContract): DatabaseDao = contract.testDao()

    @Singleton
    @Provides
    fun provideDatabase(context: Context): DatabaseContract {
        return Room.databaseBuilder(context, TestDatabase::class.java, DATABASE_NAME).build()
    }
}
