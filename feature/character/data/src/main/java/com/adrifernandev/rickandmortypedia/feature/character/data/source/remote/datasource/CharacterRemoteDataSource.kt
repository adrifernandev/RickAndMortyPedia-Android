package com.adrifernandev.rickandmortypedia.feature.character.data.source.remote.datasource

import com.adrifernandev.rickandmortypedia.feature.character.data.source.remote.entity.response.CharacterResponse

interface CharacterRemoteDataSource {

    suspend fun getCharacters(page: Int): Result<CharacterResponse?>
}