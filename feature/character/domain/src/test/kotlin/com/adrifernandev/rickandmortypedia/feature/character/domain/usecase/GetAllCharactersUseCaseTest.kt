package com.adrifernandev.rickandmortypedia.feature.character.domain.usecase

import app.cash.turbine.test
import com.adrifernandev.rickandmortypedia.feature.character.domain.model.Character
import com.adrifernandev.rickandmortypedia.feature.character.domain.model.CharacterGender
import com.adrifernandev.rickandmortypedia.feature.character.domain.model.CharacterLocation
import com.adrifernandev.rickandmortypedia.feature.character.domain.model.CharacterStatus
import com.adrifernandev.rickandmortypedia.feature.character.domain.repository.CharacterRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class GetAllCharactersUseCaseTest {

    private val characterRepository: CharacterRepository = mockk()
    private val getAllCharactersUseCase = GetAllCharactersUseCase(characterRepository)

    @Test
    fun `invoke should return characters sorted by id`() = runBlocking {
        // Given
        val characters = listOf(
            Character(
                id = 1,
                name = "Rick Sanchez",
                status = CharacterStatus.Alive,
                species = "",
                type = "",
                gender = CharacterGender.Male,
                origin = CharacterLocation(
                    name = "Earth",
                    url = "https://rickandmortyapi.com/api/location/1"
                ),
                location = CharacterLocation(
                    name = "Earth",
                    url = "https://rickandmortyapi.com/api/location/1"
                ),
                image = "",
                episode = emptyList(),
                url = "",
            ),
            Character(
                id = 2,
                name = "Rick Sanchez",
                status = CharacterStatus.Alive,
                species = "",
                type = "",
                gender = CharacterGender.Male,
                origin = CharacterLocation(
                    name = "Earth",
                    url = "https://rickandmortyapi.com/api/location/1"
                ),
                location = CharacterLocation(
                    name = "Earth",
                    url = "https://rickandmortyapi.com/api/location/1"
                ),
                image = "",
                episode = emptyList(),
                url = "",
            ),
        )
        val expectedSortedCharacters = characters.sortedBy { it.id }
        coEvery { characterRepository.getAllCharacters() } returns flowOf(Result.success(characters))

        // When
        val result = getAllCharactersUseCase.invoke()

        // Then
        result.test {
            assertEquals(expectedSortedCharacters, awaitItem().getOrNull())
            cancelAndIgnoreRemainingEvents()
        }
    }
}