package com.rudo.rickAndMorty.data.repository

import com.rudo.rickAndMorty.data.dataSource.RickAndMortyDataSource
import com.rudo.rickAndMorty.data.dataSource.dto.CharacterDetailDto
import com.rudo.rickAndMorty.data.dataSource.dto.CharacterDto
import com.rudo.rickAndMorty.data.dataSource.dto.LocationDto
import com.rudo.rickAndMorty.data.dataSource.dto.OriginDto
import com.rudo.rickAndMorty.domain.RickAndMortyRepository
import com.rudo.rickAndMorty.domain.entity.Character
import com.rudo.rickAndMorty.domain.entity.CharacterDetail
import com.rudo.rickAndMorty.domain.entity.Location
import com.rudo.rickAndMorty.domain.entity.Origin
import jakarta.inject.Inject

class RickAndMortyRepositoryImpl @Inject constructor( val dataSource: RickAndMortyDataSource): RickAndMortyRepository {
    override suspend fun getCharacters(page: Int?): List<Character> {
        val characters = dataSource.getCharacters(page = page)
        return  characters.map { it.toDomain() }
    }

    override suspend fun getCharacterById(id: Int): CharacterDetail {
        return dataSource.getCharacterById(id).toDomain()
    }

    override suspend fun searchCharacters(query: String): List<Character> {
        val characters = dataSource.searchCharacters(query)
        return characters.results.map { it.toDomain() }
    }

    fun CharacterDto.toDomain(): Character {
        return Character(
            id = this.id,
            name = this.name,
            status = this.status
            , species = this.species,
            type = this.type,
            gender = this.gender,
            origin = this.origin.toDomain(),
            location = this.location.toDomain(),
            episode = this.episode,
            image = this.image)
    }

    fun CharacterDetailDto.toDomain(): CharacterDetail {
        return CharacterDetail(
            id = this.id,
            name = this.name,
            status = this.status,
            species = this.species,
            type = this.type,
            gender = this.gender,
            origin = this.origin.toDomain(),
            location = this.location.toDomain(),
            image = this.image,
            episode = this.episode,
            url = this.url,
            created = this.created
        )
    }

    fun OriginDto.toDomain(): Origin {
        return Origin(
            name = this.name,
            url = this.url
        )
    }

    fun LocationDto.toDomain(): Location{
        return Location(
            name = this.name,
            url = this.url
        )
    }
}



