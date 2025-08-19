package com.rudo.rickcom.rudo.rickes.rudo.archetypeandroidmortymorty.presentation.navigation

import kotlinx.serialization.Serializable

sealed class Screen {
    @Serializable
    data object MainScreen : Screen()
}