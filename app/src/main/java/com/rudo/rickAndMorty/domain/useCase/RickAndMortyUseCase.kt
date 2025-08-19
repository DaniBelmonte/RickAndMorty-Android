package com.rudo.rickAndMorty.domain.useCase

import com.rudo.rickAndMorty.domain.entity.Character

interface RickAndMortyUseCase {
    suspend fun getCharacters(id: Int?): List<Character>
}
