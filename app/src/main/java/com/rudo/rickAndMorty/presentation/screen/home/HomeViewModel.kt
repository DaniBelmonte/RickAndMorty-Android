package com.rudo.rickAndMorty.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rudo.rickAndMorty.data.dataSource.RickAndMortyDataSourceImpl
import com.rudo.rickAndMorty.data.repository.RickAndMortyRepositoryImpl
import com.rudo.rickAndMorty.domain.entity.Character
import com.rudo.rickAndMorty.domain.useCase.RickAndMortyUseCase
import com.rudo.rickAndMorty.domain.useCase.RickAndMortyUseCaseImpl
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class HomeUIState(
    val characters: List<Character> = emptyList(),
    var query: String = ""
)
class HomeViewModel: ViewModel() {

    private var useCase: RickAndMortyUseCase

    private val _uiState = MutableStateFlow(HomeUIState())
    val uiState = _uiState.asStateFlow()
    var page = 1

    var searchJob: Job? = null
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

    fun loadMoreCharacters() {
        viewModelScope.launch {
            val newCharacters = useCase.getCharacters(page + 1)
            page++
            _uiState.update {
                it.copy(characters = it.characters + newCharacters)
            }
        }
    }

    fun getCharactersByName(name: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            try {
                _uiState.update {
                    it.copy(
                        query = name
                    )
                }
                _uiState.update {
                    it.copy(
                        characters = useCase.searchCharacters(name),
                    )
                }
            } catch (
                e: Exception
            ) {
                _uiState.update {
                    it.copy(
                        characters = emptyList()
                    )
                }
            }
        }
    }
}