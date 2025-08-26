package com.rudo.rickAndMorty.presentation.screen.characterDetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.rudo.rickAndMorty.domain.entity.CharacterDetail
import com.rudo.rickAndMorty.domain.useCase.FavoritesUseCase
import com.rudo.rickAndMorty.domain.useCase.RickAndMortyUseCase
import com.rudo.rickAndMorty.presentation.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class DetailUiState(
    val character: CharacterDetail? = null,
    var isFavorite: Boolean = false,
)

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private var useCase: RickAndMortyUseCase,
    private var favoritesUseCase: FavoritesUseCase,
    savedStateHandle: SavedStateHandle,
): ViewModel() {
    private val id = savedStateHandle.toRoute<Screen.DetailScreen>().id
    private var character: CharacterDetail? = null

    private val _uiState = MutableStateFlow(DetailUiState())
    val uiState = _uiState.asStateFlow()


    init {
        loadCharacter()
        loadFavorite()
    }

    fun loadCharacter(){
        viewModelScope.launch {
            _uiState.update {
                it.copy(character = useCase.getCharacterById(id))

            }
        }
    }

    fun loadFavorite() {
        viewModelScope.launch {
            favoritesUseCase.isFavorite(id = id).collect { isFavorite ->
                _uiState.update {
                    it.copy(isFavorite = isFavorite)
                }
            }
        }
    }

    fun toggleFavorite(){
        viewModelScope.launch {
            favoritesUseCase.toggle(id)
        }
    }
}
