package com.imgur.database_api.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TEST_TABLE")
data class StringDto(
    @PrimaryKey val value: String
)