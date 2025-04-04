package com.adrifernandev.rickandmortypedia.feature.character.data.source.remote.service

import com.adrifernandev.rickandmortypedia.feature.character.data.source.remote.entity.response.CharacterResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterService {

    companion object {
        private const val CHARACTER_PATH = "character"
        private const val QUERY_PAGE = "page"
    }

    @GET(CHARACTER_PATH)
    suspend fun getCharacters(
        @Query(QUERY_PAGE) page: Int
    ): Response<CharacterResponse?>
}