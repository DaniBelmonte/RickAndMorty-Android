package com.rudo.rickAndMorty.data.dataSource.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class OriginDto(
    val name: String,
    val url: String
)
