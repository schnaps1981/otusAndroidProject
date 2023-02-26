package com.imgur.database_impl

import androidx.room.Database
import androidx.room.RoomDatabase
import com.imgur.database_api.DatabaseContract
import com.imgur.database_api.dto.StringDto

@Database(entities = [StringDto::class], version = 1, exportSchema = false)
abstract class TestDatabase: RoomDatabase(), DatabaseContract {
}