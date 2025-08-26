package com.rudo.rickAndMorty.domain.useCase

import kotlinx.coroutines.flow.Flow

interface FavoritesUseCase {
    suspend fun isFavorite(id: Int): Flow<Boolean>
    suspend fun add(id: Int)
    suspend fun remove(id: Int)
    suspend fun toggle(id: Int)
}