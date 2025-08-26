package com.rudo.rickAndMorty.data.dataSource.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class LocationDto(
    val name: String,
    val url: String
)
