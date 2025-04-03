package com.adrifernandev.rickandmortypedia.feature.character.domain.usecase

import com.adrifernandev.rickandmortypedia.feature.character.domain.model.Character
import com.adrifernandev.rickandmortypedia.feature.character.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetAllCharactersUseCase @Inject constructor(
    private val characterRepository: CharacterRepository
) {
    operator fun invoke(): Flow<Result<List<Character>?>> =
        characterRepository.getAllCharacters().map { result ->
            result.mapCatching { characters ->
                characters?.sortedBy { it.name }
            }
        }
}