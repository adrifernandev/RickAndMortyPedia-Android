package com.adrifernandev.rickandmortypedia.feature.character.data.source.remote.service

import com.adrifernandev.rickandmortypedia.feature.character.data.source.remote.entity.response.CharacterResponse
import retrofit2.Response
import retrofit2.http.POST

interface CharacterService {

    companion object {
        private const val CHARACTER_PATH = "/character"
    }

    @POST(CHARACTER_PATH)
    suspend fun getAllCharacters(): Response<CharacterResponse?>
}