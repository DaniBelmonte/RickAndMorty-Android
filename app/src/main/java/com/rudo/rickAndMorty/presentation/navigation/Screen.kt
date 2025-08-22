package com.rudo.rickAndMorty.presentation.navigation

import kotlinx.serialization.Serializable

sealed class Screen {
    @Serializable
    data object MainScreen : Screen()
    @Serializable
    data class DetailScreen(val id: Int) : Screen()
}