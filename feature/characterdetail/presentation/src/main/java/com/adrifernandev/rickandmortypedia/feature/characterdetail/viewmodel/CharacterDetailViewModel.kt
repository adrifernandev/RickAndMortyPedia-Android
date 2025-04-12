package com.adrifernandev.rickandmortypedia.feature.characterdetail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adrifernandev.rickandmortypedia.episode.domain.model.Episode
import com.adrifernandev.rickandmortypedia.episode.domain.usecase.GetEpisodesByIdsUseCase
import com.adrifernandev.rickandmortypedia.feature.character.domain.model.Character
import com.adrifernandev.rickandmortypedia.feature.character.domain.utils.extractEpisodesIdsFromCharacter
import com.adrifernandev.rickandmortypedia.feature.location.domain.model.Location
import com.adrifernandev.rickandmortypedia.feature.location.domain.usecase.GetLocationsByIdsUseCase
import com.adrifernandev.rickandmortypedia.feature.location.domain.utils.extractLocationIdFromUrl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val getLocationsByIdsUseCase: GetLocationsByIdsUseCase,
    private val getEpisodesByIdsUseCase: GetEpisodesByIdsUseCase
) : ViewModel() {

    data class UIState(
        val character: Character? = null,
        val originLocation: Location? = null,
        val lastKnownLocation: Location? = null,
        val isLocationLoading: Boolean = false,
        val episodes: List<Episode>? = null
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
                //TODO: Check if this is a good way to do concurrent calls
                getCharacterOriginAndLastKnownLocations()
                getCharacterEpisodes()
            }
        }
    }

    private fun getCharacterOriginAndLastKnownLocations() {
        _state.value.character?.let {
            //TODO: Is this logic well implemented here? Shouldn't it be in the useCase????
            val originLocationId = extractLocationIdFromUrl(it.origin.url)
            val lastKnownLocationId = extractLocationIdFromUrl(it.location.url)
            val listWithIds = listOfNotNull(
                originLocationId,
                lastKnownLocationId
            )

            viewModelScope.launch {
                _state.value = _state.value.copy(
                    isLocationLoading = true
                )
                getLocationsByIdsUseCase.invoke(listWithIds).onSuccess { locations ->
                    val originLocation =
                        locations.find { location -> location.id == originLocationId }
                    val lastKnownLocation =
                        locations.find { location -> location.id == lastKnownLocationId }

                    _state.update { state ->
                        state.copy(
                            originLocation = originLocation,
                            lastKnownLocation = lastKnownLocation
                        )
                    }
                }.onFailure {
                    //TODO: Handle error
                }
            }
            _state.update { state ->
                state.copy(isLocationLoading = false)
            }
        }
    }

    private fun getCharacterEpisodes() {
        _state.value.character?.let {
            viewModelScope.launch {
                _state.value = _state.value.copy(
                    isLocationLoading = true
                )
                val episodesIds = extractEpisodesIdsFromCharacter(it)
                getEpisodesByIdsUseCase.invoke(episodesIds).onSuccess { episodes ->
                    _state.update {
                        it.copy(
                            episodes = episodes
                        )
                    }
                }.onFailure {
                    //TODO: Handle error
                }
            }
        }
    }
}