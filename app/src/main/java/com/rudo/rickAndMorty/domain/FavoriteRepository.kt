package com.rudo.rickAndMorty.domain

// domain/repository/FavoritesRepository.kt
import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {

    suspend fun getAllFavoriteIds(): Flow<List<Int>>
    suspend fun isFavorite(id: Int): Flow<Boolean>
    suspend fun add(id: Int)
    suspend fun remove(id: Int)
    suspend fun toggle(id: Int)
}