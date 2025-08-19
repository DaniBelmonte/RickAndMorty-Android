package com.rudo.rickAndMorty.presentation.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rudo.rickAndMorty.data.dataSource.RickAndMortyDataSourceImpl
import com.rudo.rickAndMorty.data.repository.RickAndMortyRepositoryImpl
import com.rudo.rickAndMorty.domain.entity.Character
import com.rudo.rickAndMorty.domain.useCase.RickAndMortyUseCase
import com.rudo.rickAndMorty.domain.useCase.RickAndMortyUseCaseImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


data class HomeUIState(
    val characters: List<Character> = emptyList()
)
class HomeViewModel: ViewModel() {

    private var useCase: RickAndMortyUseCase

    private val _uiState = MutableStateFlow(HomeUIState())
    val uiState = _uiState.asStateFlow()

    init {
        val dataSource = RickAndMortyDataSourceImpl()
        val repository = RickAndMortyRepositoryImpl(dataSource)
        this.useCase = RickAndMortyUseCaseImpl(repository)
        fetchCharacters()
    }

    fun fetchCharacters() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(characters = useCase.getCharacters(1))
            }
        }
    }
}