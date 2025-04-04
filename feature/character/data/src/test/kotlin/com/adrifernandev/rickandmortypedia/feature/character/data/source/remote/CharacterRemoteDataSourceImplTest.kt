package com.adrifernandev.rickandmortypedia.feature.character.data.source.remote

import com.adrifernandev.rickandmortypedia.feature.character.data.source.remote.datasource.CharacterRemoteDataSourceImpl
import com.adrifernandev.rickandmortypedia.feature.character.data.source.remote.entity.response.CharacterResponse
import com.adrifernandev.rickandmortypedia.feature.character.data.source.remote.entity.response.InfoResponse
import com.adrifernandev.rickandmortypedia.feature.character.data.source.remote.service.CharacterService
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import retrofit2.Response
import kotlin.test.assertTrue

class CharacterRemoteDataSourceImplTest {

    private val characterService: CharacterService = mockk()
    private val dataSource = CharacterRemoteDataSourceImpl(characterService)

    @Test
    fun `getAllCharacters returns success when API call is successful`() = runBlocking {
        // Given
        val mockResponse =
            CharacterResponse(info = InfoResponse(1, 1, null, null), results = emptyList())
        coEvery { characterService.getCharacters(1) } returns Response.success(mockResponse)

        // When
        val result = dataSource.getCharacters(1).first()

        // Then
        assertTrue(result.isSuccess)
        assertEquals(mockResponse, Result.success(mockResponse).getOrNull())
    }

    @Test
    fun `getAllCharacters returns error when API call fails`() = runBlocking {
        // Given
        val mockException = Exception("Internal Server Error")
        coEvery { characterService.getCharacters(1) } returns Response.error(
            500,
            mockk(relaxed = true)
        )

        // When
        val result = dataSource.getCharacters(1).first()

        // Then
        assertTrue(result.isFailure)
        assertEquals(mockException.message, result.exceptionOrNull()?.message)
    }
}
