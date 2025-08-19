package com.rudo.rickAndMorty.domain.useCase

import com.rudo.rickAndMorty.domain.RickAndMortyRepository
import com.rudo.rickAndMorty.domain.entity.Character
import jakarta.inject.Inject

class RickAndMortyUseCaseImpl @Inject constructor(val repository: RickAndMortyRepository): RickAndMortyUseCase {
    override suspend fun getCharacters(id: Int?): List<Character> {
        return repository.getCharacters()
    }
}
