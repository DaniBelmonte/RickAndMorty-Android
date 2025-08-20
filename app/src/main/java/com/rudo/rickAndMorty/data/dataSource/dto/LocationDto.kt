package com.rudo.rickAndMorty.data.dataSource.dto

import kotlinx.serialization.Serializable

@Serializable
data class LocationDto(
    val name: String,
    val url: String
)
