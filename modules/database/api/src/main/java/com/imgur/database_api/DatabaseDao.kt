package com.imgur.database_api

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.imgur.database_api.dto.StringDto

@Dao
interface DatabaseDao {

    @Query("SELECT * FROM TEST_TABLE")
    suspend fun getAllDbRecords(): List<String>

    @Insert
    suspend fun addDbRecord(value: StringDto)
}
