package com.imgur.database_impl

import androidx.room.Database
import androidx.room.RoomDatabase
import com.imgur.database_api.DatabaseContract
import com.imgur.database_api.dto.FavoriteItem

@Database(entities = [FavoriteItem::class], version = 1, exportSchema = false)
abstract class FavoriteDatabase : RoomDatabase(), DatabaseContract