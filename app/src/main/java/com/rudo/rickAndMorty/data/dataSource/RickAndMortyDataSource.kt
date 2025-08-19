package com.rudo.rickAndMorty.data.dataSource

import com.rudo.rickAndMorty.data.dataSource.dto.CharacterDto

interface RickAndMortyDataSource {
    suspend fun getCharacters(page: Int?): List<CharacterDto>
}
