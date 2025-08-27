package com.rudo.rickAndMorty.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rudo.rickAndMorty.data.dataSource.remote.RickAndMortyDataSourceImpl
import com.rudo.rickAndMorty.data.dataSource.remote.dto.CharacterDto
import com.rudo.rickAndMorty.data.repository.RickAndMortyRepositoryImpl
import com.rudo.rickAndMorty.domain.entity.Character
import com.rudo.rickAndMorty.domain.useCase.FavoritesUseCase
import com.rudo.rickAndMorty.domain.useCase.RickAndMortyUseCase
import com.rudo.rickAndMorty.domain.useCase.RickAndMortyUseCaseImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class HomeUIState(
    val characters: List<Character> = emptyList(),
    var query: String = "",
    var isFavorite: Boolean = false
)
@HiltViewModel
class HomeViewModel @Inject constructor(
    private var useCase: RickAndMortyUseCase,
    private var favoriteUseCase: FavoritesUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow(HomeUIState())
    val uiState = _uiState.asStateFlow()
    var page = 1
    private var favoriteIds: List<Int> = emptyList()

    var searchJob: Job? = null
    init {
        loadFavorites()
        fetchCharacters()
    }

    fun fetchCharacters() {
        viewModelScope.launch {
            val characters = useCase.getCharacters(page)
            _uiState.update { it.copy(characters = applyFavorites(characters)) }
        }
    }

    fun loadMoreCharacters() {
        viewModelScope.launch {
            val nextPage = page + 1
            val newCharacters = useCase.getCharacters(nextPage)
            page = nextPage
            _uiState.update { it.copy(characters = it.characters + applyFavorites(newCharacters)) }
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
                val result = useCase.searchCharacters(name)
                _uiState.update {
                    it.copy(
                        characters = applyFavorites(result)
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

    private fun loadFavorites() {
        viewModelScope.launch {
            favoriteUseCase.getFavorites().collect { ids ->
                favoriteIds = ids
                _uiState.update { it ->
                    it.copy(
                        characters = it.characters.map { character ->
                            character.copy(isFavorite = favoriteIds.contains(character.id))
                        }
                    )
                }
            }
        }
    }

    private fun applyFavorites(characters: List<Character>): List<Character> {
        return characters.map { character -> character.copy(isFavorite = favoriteIds.contains(character.id)) }
    }
}