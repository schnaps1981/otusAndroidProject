package com.imgur.favorites.repository

import com.imgur.favorites.entity.FavoriteEntity

interface FavoriteRepository {

    suspend fun getFavorites(): List<FavoriteEntity>

    suspend fun deleteById(id: String): Int
}
