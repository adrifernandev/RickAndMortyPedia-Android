package com.adrifernandev.rickandmortypedia.feature.character.data.repository

import com.adrifernandev.rickandmortypedia.feature.character.data.repository.factory.CharacterFactory
import com.adrifernandev.rickandmortypedia.feature.character.domain.mapper.toDomainModel
import com.adrifernandev.rickandmortypedia.feature.character.domain.model.Character
import com.adrifernandev.rickandmortypedia.feature.character.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val characterFactory: CharacterFactory
) : CharacterRepository {

    override fun getAllCharacters(): Flow<Result<List<Character>?>> {
        return characterFactory.remoteDataStore.getAllCharacters()
            .map { result ->
                result.mapCatching { characterResponse ->
                    characterResponse?.toDomainModel()
                }
            }
    }
}