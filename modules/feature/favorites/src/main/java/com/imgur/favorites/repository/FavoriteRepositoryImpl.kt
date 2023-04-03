package com.imgur.favorites.repository

import com.imgur.database_api.FavoritesDao
import com.imgur.favorites.entity.FavoriteEntity
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    private val favoritesDao: FavoritesDao
) : FavoriteRepository {
    override suspend fun getFavorites(): List<FavoriteEntity> {
        val favorites = favoritesDao.getFavorites()

        return favorites.map {
            FavoriteEntity.valueOf(it)
        }
    }

    override suspend fun deleteById(id: String) {
        favoritesDao.deleteById(id)
    }
}
