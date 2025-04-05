package com.adrifernandev.rickandmortypedia.feature.characterdetail.viewmodel

import androidx.lifecycle.ViewModel
import com.adrifernandev.rickandmortypedia.feature.character.domain.model.Character
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class CharacterDetailViewModel(

) : ViewModel() {

    data class UIState(
        val character: Character? = null,
    )

    sealed class UIEvent {
        data class InitializeCharacter(val character: Character) : UIEvent()
    }

    private val _state: MutableStateFlow<UIState> = MutableStateFlow(UIState())
    val state = _state.asStateFlow()

    fun onEvent(event: UIEvent) {
        when (event) {
            is UIEvent.InitializeCharacter -> {
                _state.value = _state.value.copy(character = event.character)
            }
        }
    }
}