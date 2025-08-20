package com.rudo.rickAndMorty.domain.useCase

import com.rudo.rickAndMorty.domain.entity.Character
import com.rudo.rickAndMorty.domain.entity.CharacterDetail

interface RickAndMortyUseCase {
    suspend fun getCharacters(page: Int?): List<Character>
    suspend fun getCharacterById(id: Int): CharacterDetail
    suspend fun searchCharacters(query: String): List<Character>
}
