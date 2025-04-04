package com.adrifernandev.rickandmortypedia.feature.character.data.source.remote.datasource

import com.adrifernandev.rickandmortypedia.common.data.network.apiRequestFlow
import com.adrifernandev.rickandmortypedia.feature.character.data.source.remote.entity.response.CharacterResponse
import com.adrifernandev.rickandmortypedia.feature.character.data.source.remote.service.CharacterService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharacterRemoteDataSourceImpl @Inject constructor(
    private val characterService: CharacterService
) : CharacterRemoteDataSource {

    override fun getCharacters(page: Int): Flow<Result<CharacterResponse?>> =
        apiRequestFlow(
            call = { characterService.getCharacters(page) },
        )
}