package com.imgur.database_api

interface DatabaseProvider {
    fun provideDatabase(): DatabaseContract

    fun provideFavoritesDao(): FavoritesDao
}
