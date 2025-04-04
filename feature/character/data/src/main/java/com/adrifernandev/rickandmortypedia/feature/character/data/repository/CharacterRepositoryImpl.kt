package com.adrifernandev.rickandmortypedia.feature.character.data.repository

import com.adrifernandev.rickandmortypedia.feature.character.data.mapper.toDomainModel
import com.adrifernandev.rickandmortypedia.feature.character.data.repository.factory.CharacterFactory
import com.adrifernandev.rickandmortypedia.feature.character.domain.model.PagedCharacters
import com.adrifernandev.rickandmortypedia.feature.character.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val characterFactory: CharacterFactory
) : CharacterRepository {

    override fun getCharacters(page: Int): Flow<Result<PagedCharacters?>> {
        return characterFactory.remoteDataStore.getCharacters(page)
            .map { response ->
                response.mapCatching {
                    it?.toDomainModel()
                }
            }
    }
}