package com.rudo.rickAndMorty.domain

import com.rudo.rickAndMorty.domain.entity.Character
import com.rudo.rickAndMorty.domain.entity.CharacterDetail

interface RickAndMortyRepository {
    suspend fun getCharacters(page: Int? = null): List<Character>
    suspend fun getCharacterById(id: Int): CharacterDetail
    suspend fun searchCharacters(query: String): List<Character>
}
