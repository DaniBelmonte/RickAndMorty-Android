package com.rudo.rickAndMorty.data.dataSource.dto

import kotlinx.serialization.Serializable

@Serializable
data class CharactersResponseDto(
    val results: List<CharacterDto>,
    val info: InfoDto,
)

@Serializable
data class InfoDto(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
)

@Serializable
data class CharacterDto(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: OriginDto,
    val location: LocationDto,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: String
)

@Serializable
data class OriginDto(
    val name: String,
    val url: String
)

@Serializable
data class LocationDto(
    val name: String,
    val url: String
)
