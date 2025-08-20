package com.rudo.rickAndMorty.domain.useCase

import com.rudo.rickAndMorty.domain.RickAndMortyRepository
import com.rudo.rickAndMorty.domain.entity.Character
import com.rudo.rickAndMorty.domain.entity.CharacterDetail
import jakarta.inject.Inject

class RickAndMortyUseCaseImpl @Inject constructor(val repository: RickAndMortyRepository): RickAndMortyUseCase {
    override suspend fun getCharacters(id: Int?): List<Character> {
        return repository.getCharacters()
    }

    override suspend fun getCharacterById(id: Int): CharacterDetail {
        return repository.getCharacterById(id)
    }

    override suspend fun searchCharacters(query: String): List<Character> {
        return repository.searchCharacters(query)
    }
}
