package com.adrifernandev.rickandmortypedia.feature.character.domain.repository

import com.adrifernandev.rickandmortypedia.feature.character.domain.model.PagedCharacters
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {

    fun getCharacters(page: Int): Flow<Result<PagedCharacters?>>
}