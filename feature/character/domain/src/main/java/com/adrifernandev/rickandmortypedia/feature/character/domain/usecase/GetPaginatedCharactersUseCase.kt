package com.adrifernandev.rickandmortypedia.feature.character.domain.usecase

import com.adrifernandev.rickandmortypedia.feature.character.domain.model.PagedCharacters
import com.adrifernandev.rickandmortypedia.feature.character.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPaginatedCharactersUseCase @Inject constructor(
    private val characterRepository: CharacterRepository
) {
    operator fun invoke(page: Int): Flow<Result<PagedCharacters?>> =
        characterRepository.getCharacters(page)
}