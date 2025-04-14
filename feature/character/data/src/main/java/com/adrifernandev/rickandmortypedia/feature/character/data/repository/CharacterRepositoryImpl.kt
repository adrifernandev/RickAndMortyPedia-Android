package com.adrifernandev.rickandmortypedia.feature.character.data.repository

import com.adrifernandev.rickandmortypedia.feature.character.data.mapper.toDomainModel
import com.adrifernandev.rickandmortypedia.feature.character.data.repository.factory.CharacterFactory
import com.adrifernandev.rickandmortypedia.feature.character.domain.model.PagedCharacters
import com.adrifernandev.rickandmortypedia.feature.character.domain.repository.CharacterRepository
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val characterFactory: CharacterFactory
) : CharacterRepository {

    override suspend fun getCharacters(page: Int): Result<PagedCharacters?> {
        return characterFactory.remoteDataStore.getCharacters(page)
            .mapCatching { response ->
                response?.toDomainModel()
            }
    }
}