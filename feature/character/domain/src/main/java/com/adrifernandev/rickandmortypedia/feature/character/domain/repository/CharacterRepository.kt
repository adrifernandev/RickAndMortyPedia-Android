package com.adrifernandev.rickandmortypedia.feature.character.domain.repository

import com.adrifernandev.rickandmortypedia.feature.character.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {

    fun getAllCharacters(): Flow<Result<List<Character>?>>
}