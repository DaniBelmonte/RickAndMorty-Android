package com.rudo.rickAndMorty.domain

import com.rudo.rickAndMorty.domain.entity.Character

interface RickAndMortyRepository {
    suspend fun getCharacters(page: Int? = null): List<Character>
}
