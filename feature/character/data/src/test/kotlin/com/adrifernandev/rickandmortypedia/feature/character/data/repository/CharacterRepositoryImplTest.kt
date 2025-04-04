package com.adrifernandev.rickandmortypedia.feature.character.data.repository

import app.cash.turbine.test
import com.adrifernandev.rickandmortypedia.feature.character.data.repository.factory.CharacterFactory
import com.adrifernandev.rickandmortypedia.feature.character.data.source.remote.entity.response.CharacterItemResponse
import com.adrifernandev.rickandmortypedia.feature.character.data.source.remote.entity.response.CharacterResponse
import com.adrifernandev.rickandmortypedia.feature.character.data.source.remote.entity.response.InfoResponse
import com.adrifernandev.rickandmortypedia.feature.character.data.source.remote.entity.response.LocationResponse
import com.adrifernandev.rickandmortypedia.feature.character.domain.model.Character
import com.adrifernandev.rickandmortypedia.feature.character.domain.model.CharacterGender
import com.adrifernandev.rickandmortypedia.feature.character.domain.model.CharacterLocation
import com.adrifernandev.rickandmortypedia.feature.character.domain.model.CharacterStatus
import com.adrifernandev.rickandmortypedia.feature.character.domain.model.InfoModel
import com.adrifernandev.rickandmortypedia.feature.character.domain.model.PagedCharacters
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class CharacterRepositoryImplTest {

    private val characterFactory: CharacterFactory = mockk()
    private val characterRepository: CharacterRepositoryImpl = CharacterRepositoryImpl(
        characterFactory
    )

    @Test
    fun `getAllCharacters should return success domain model`() = runBlocking {
        // Given
        val mockResponse = CharacterResponse(
            info = InfoResponse(count = 1, pages = 1, next = null, prev = null),
            results = listOf(
                CharacterItemResponse(
                    id = 1,
                    name = "Rick Sanchez",
                    status = "Alive",
                    species = "Human",
                    type = "",
                    gender = "Male",
                    origin = LocationResponse(
                        name = "Earth",
                        url = "https://rickandmortyapi.com/api/location/1"
                    ),
                    location = LocationResponse(
                        name = "Earth",
                        url = "https://rickandmortyapi.com/api/location/1"
                    ),
                    image = "",
                    episode = emptyList(),
                    url = "",
                    created = ""
                )
            )
        )

        val characterList = listOf(
            Character(
                id = 1,
                name = "Rick Sanchez",
                status = CharacterStatus.Alive,
                species = "Human",
                gender = CharacterGender.Male,
                image = "",
                type = "",
                origin = CharacterLocation(
                    name = "Earth",
                    url = "https://rickandmortyapi.com/api/location/1"
                ),
                location = CharacterLocation(
                    name = "Earth",
                    url = "https://rickandmortyapi.com/api/location/1"
                ),
                episode = emptyList(),
                url = ""
            )
        )

        val expectedPagedModel = PagedCharacters(
            info = InfoModel(
                count = 1,
                pages = 1,
                next = null,
                prev = null
            ),
            results = characterList
        )

        coEvery { characterFactory.remoteDataStore.getCharacters(1) } returns flowOf(
            Result.success(
                mockResponse
            )
        )

        // When
        characterRepository.getCharacters(1).test {
            val result = awaitItem()
            assert(result.isSuccess)
            assertEquals(expectedPagedModel, result.getOrNull())
            cancelAndIgnoreRemainingEvents()
        }
    }
}