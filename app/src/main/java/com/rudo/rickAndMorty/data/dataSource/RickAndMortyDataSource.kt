package com.rudo.rickAndMorty.data.dataSource

import com.rudo.rickAndMorty.data.dataSource.dto.CharacterDetailDto
import com.rudo.rickAndMorty.data.dataSource.dto.CharacterDto
import com.rudo.rickAndMorty.data.dataSource.dto.CharactersResponseDto

interface RickAndMortyDataSource {
    suspend fun getCharacters(page: Int? = null): List<CharacterDto>
    suspend fun getCharacterById(id: Int): CharacterDetailDto
    suspend fun searchCharacters(query: String): CharactersResponseDto
}
