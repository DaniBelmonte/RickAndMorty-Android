package com.rudo.rickAndMorty.presentation.screen.characterDetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.toRoute
import com.rudo.rickAndMorty.data.dataSource.RickAndMortyDataSource
import com.rudo.rickAndMorty.data.dataSource.RickAndMortyDataSourceImpl
import com.rudo.rickAndMorty.data.repository.RickAndMortyRepositoryImpl
import com.rudo.rickAndMorty.domain.entity.Character
import com.rudo.rickAndMorty.domain.entity.CharacterDetail
import com.rudo.rickAndMorty.domain.useCase.RickAndMortyUseCase
import com.rudo.rickAndMorty.domain.useCase.RickAndMortyUseCaseImpl
import com.rudo.rickAndMorty.presentation.navigation.Screen
import com.rudo.rickAndMorty.presentation.screen.home.HomeUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject



data class DetailUiState(
    val character: CharacterDetail? = null
)

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
): ViewModel() {
    private val id = savedStateHandle.toRoute<Screen.DetailScreen>().id
    private var character: CharacterDetail? = null
    private var useCase: RickAndMortyUseCase
    private val _uiState = MutableStateFlow(DetailUiState())
    val uiState = _uiState.asStateFlow()


    init {
        val dataSource = RickAndMortyDataSourceImpl()
        val repository = RickAndMortyRepositoryImpl(dataSource)
        this.useCase = RickAndMortyUseCaseImpl(repository)
        loadCharacter()
    }

    fun loadCharacter(){
        viewModelScope.launch {
            _uiState.update {
                it.copy(character = useCase.getCharacterById(id))

            }
        }
    }
}
