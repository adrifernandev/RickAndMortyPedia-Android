package com.adrifernandev.rickandmortypedia.feature.character.domain.usecase

import com.adrifernandev.rickandmortypedia.feature.character.domain.model.PagedCharacters
import com.adrifernandev.rickandmortypedia.feature.character.domain.repository.CharacterRepository
import javax.inject.Inject

class GetPaginatedCharactersUseCase @Inject constructor(
    private val characterRepository: CharacterRepository
) {
    suspend operator fun invoke(page: Int): Result<PagedCharacters?> =
        characterRepository.getCharacters(page)
}