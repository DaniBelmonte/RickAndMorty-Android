package com.rudo.rickAndMorty.data.dataSource.dto

import kotlinx.serialization.Serializable

@Serializable
data class OriginDto(
    val name: String,
    val url: String
)
