package com.adrifernandev.rickandmortypedia.feature.home.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adrifernandev.rickandmortypedia.feature.character.domain.model.Character
import com.adrifernandev.rickandmortypedia.feature.character.domain.usecase.GetAllCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    getAllCharactersUseCase: GetAllCharactersUseCase
) : ViewModel() {

    data class UIState(
        val characters: List<Character>? = null,
    )

    private val _state: MutableStateFlow<UIState> =
        MutableStateFlow(UIState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            getAllCharactersUseCase.invoke().collect { result ->
                result.onSuccess { characters ->
                    _state.value = state.value.copy(
                        characters = characters
                    )
                }
            }
        }
    }

}