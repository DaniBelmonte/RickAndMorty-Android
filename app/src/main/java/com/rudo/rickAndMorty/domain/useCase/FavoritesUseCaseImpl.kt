package com.rudo.rickAndMorty.domain.useCase

import com.rudo.rickAndMorty.domain.FavoritesRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class FavoritesUseCaseImpl @Inject constructor(val repository: FavoritesRepository): FavoritesUseCase{
    override suspend fun getFavorites(): Flow<List<Int>> {
        return repository.getAllFavoriteIds()
    }

    override suspend fun isFavorite(id: Int): Flow<Boolean> {
        return repository.isFavorite(id)
    }

    override suspend fun add(id: Int) {
        repository.add(id)
    }

    override suspend fun remove(id: Int) {
        repository.remove(id)
    }

    override suspend fun toggle(id: Int) {
        repository.toggle(id)
    }
}