package com.adrifernandev.rickandmortypedia.feature.character.data.source.remote.datasource

import com.adrifernandev.rickandmortypedia.common.data.network.apiRequestCall
import com.adrifernandev.rickandmortypedia.feature.character.data.source.remote.entity.response.CharacterResponse
import com.adrifernandev.rickandmortypedia.feature.character.data.source.remote.service.CharacterService
import javax.inject.Inject

class CharacterRemoteDataSourceImpl @Inject constructor(
    private val characterService: CharacterService
) : CharacterRemoteDataSource {

    override suspend fun getCharacters(page: Int): Result<CharacterResponse?> =
        apiRequestCall(
            call = { characterService.getCharacters(page) },
        )
}