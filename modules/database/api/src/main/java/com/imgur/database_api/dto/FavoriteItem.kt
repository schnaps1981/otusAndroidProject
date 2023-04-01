package com.imgur.database_api.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.imgur.database_api.DatabaseContract

@Entity(tableName = DatabaseContract.TABLE_FAVORITES)
data class FavoriteItem(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "pk", defaultValue = "0")
    val pk: Long = 0,

    @ColumnInfo(name = "image_id", defaultValue = "")
    val imageId: String = "",

    @ColumnInfo(name = "image_url", defaultValue = "")
    val imageUrl: String = "",

    @ColumnInfo(name = "title", defaultValue = "")
    val title: String = ""
)
