package com.rudo.rickAndMorty.data.dataSource.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class CharactersResponseDto(
    val results: List<CharacterDto>,
    val info: InfoDto,
)
