package com.rudo.rickAndMorty.data.dataSource.remote

import com.rudo.rickAndMorty.data.dataSource.remote.dto.CharacterDetailDto
import com.rudo.rickAndMorty.data.dataSource.remote.dto.CharacterDto
import com.rudo.rickAndMorty.data.dataSource.remote.dto.CharactersResponseDto

interface RickAndMortyDataSource {
    suspend fun getCharacters(page: Int? = null): List<CharacterDto>
    suspend fun getCharacterById(id: Int): CharacterDetailDto
    suspend fun searchCharacters(query: String): CharactersResponseDto
}
