package com.imgur.database_impl.di

import android.content.Context
import androidx.room.Room
import com.imgur.database_api.DatabaseContract
import com.imgur.database_api.FavoritesDao
import com.imgur.database_impl.FavoriteDatabase
import dagger.Module
import dagger.Provides
import dagger.Reusable
import javax.inject.Singleton

@Module
object DatabaseModule {

    @Provides
    @Reusable
    fun provideTestDao(contract: DatabaseContract): FavoritesDao = contract.favoriteDao()

    @Singleton
    @Provides
    fun provideDatabase(context: Context): DatabaseContract {
        return Room.databaseBuilder(
            context,
            FavoriteDatabase::class.java,
            DatabaseContract.DATABASE_NAME
        ).build()
    }
}
