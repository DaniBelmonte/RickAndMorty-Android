package com.rudo.rickAndMorty.data.dataSource.local

import kotlinx.coroutines.flow.Flow

interface FavoriteLocalDataSource {
    suspend fun getAllFavoriteIds(): Flow<List<Int>>
    suspend fun isFavorite(id: Int): Flow<Boolean>
    suspend fun add(id: Int)
    suspend fun remove(id: Int)
    suspend fun toggle(id: Int)
}