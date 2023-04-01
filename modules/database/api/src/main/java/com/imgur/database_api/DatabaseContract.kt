package com.imgur.database_api

interface DatabaseContract {

    fun favoriteDao(): FavoritesDao

    companion object {
        const val DATABASE_NAME = "favorites_db" // name for backward compatibility

        const val TABLE_FAVORITES = "favorites"
    }
}
