package com.adrifernandev.rickandmortypedia.feature.character.data.source.remote.datasource

import com.adrifernandev.rickandmortypedia.feature.character.data.source.remote.entity.response.CharacterResponse
import kotlinx.coroutines.flow.Flow

interface CharacterRemoteDataSource {

    fun getCharacters(page: Int): Flow<Result<CharacterResponse?>>
}