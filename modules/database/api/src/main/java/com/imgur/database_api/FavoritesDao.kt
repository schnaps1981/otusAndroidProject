package com.imgur.database_api

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.imgur.database_api.dto.FavoriteItem

@Dao
interface FavoritesDao {

    @Query("SELECT * FROM ${DatabaseContract.TABLE_FAVORITES}")
    suspend fun getFavorites(): List<FavoriteItem>

    @Insert
    suspend fun addFavorite(value: FavoriteItem): Long

    @Query("DELETE FROM ${DatabaseContract.TABLE_FAVORITES} WHERE image_id = :id")
    fun deleteById(id: String): Int
}
