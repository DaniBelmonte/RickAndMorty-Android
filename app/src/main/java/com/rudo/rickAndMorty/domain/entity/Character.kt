package com.rudo.rickAndMorty.domain.entity

data class Character(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: Origin,
    val location: Location,
    val episode: List<String>,
    val image: String,
) {}
