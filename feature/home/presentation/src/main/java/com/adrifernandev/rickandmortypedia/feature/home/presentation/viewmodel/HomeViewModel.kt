package com.adrifernandev.rickandmortypedia.feature.home.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adrifernandev.rickandmortypedia.feature.character.domain.model.Character
import com.adrifernandev.rickandmortypedia.feature.character.domain.model.InfoModel
import com.adrifernandev.rickandmortypedia.feature.character.domain.usecase.GetPaginatedCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val FIRST_PAGE_LOAD_CHARACTERS = 1

private const val NEXT_PAGE = 1

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPaginatedCharactersUseCase: GetPaginatedCharactersUseCase
) : ViewModel() {

    data class UIState(
        val charactersList: List<Character> = emptyList(),
        val charactersInfo: InfoModel? = null,
        val currentCharacterPage: Int = 0,
        val isLoading: Boolean = false,
    )

    sealed class UIEvent {
        data object LoadNextCharacterPage : UIEvent()
    }

    sealed class UIAction {
        data object ShowError : UIAction()
    }

    private val _state: MutableStateFlow<UIState> =
        MutableStateFlow(UIState())
    val state = _state.asStateFlow()

    private val _uiAction: MutableSharedFlow<UIAction> =
        MutableSharedFlow()
    val uiAction = _uiAction.asSharedFlow()

    init {
        getCharacters(FIRST_PAGE_LOAD_CHARACTERS)
    }

    fun onEvent(event: UIEvent) {
        when (event) {
            is UIEvent.LoadNextCharacterPage -> getNextCharactersPage()
        }
    }

    private fun getCharacters(page: Int) {
        viewModelScope.launch {
            setLoading(true)
            getPaginatedCharactersUseCase.invoke(page)
                .onSuccess { result ->
                    _state.value = _state.value.copy(
                        charactersInfo = result?.info,
                        charactersList = _state.value.charactersList + (result?.results
                            ?: emptyList()),
                        currentCharacterPage = page,
                    )
                }.onFailure {
                    _uiAction.emit(UIAction.ShowError)
                }
            setLoading(false)
        }
    }

    private fun getNextCharactersPage() {
        if (_state.value.charactersInfo?.next != null && !_state.value.isLoading) {
            val nextPage = _state.value.currentCharacterPage + NEXT_PAGE
            getCharacters(nextPage)
        }
    }

    private fun setLoading(isLoading: Boolean) {
        _state.value = _state.value.copy(
            isLoading = isLoading
        )
    }
}