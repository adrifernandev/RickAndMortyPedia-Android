package com.adrifernandev.rickandmortypedia.feature.character.data.mapper

import com.adrifernandev.rickandmortypedia.feature.character.data.source.remote.entity.response.CharacterItemResponse
import com.adrifernandev.rickandmortypedia.feature.character.data.source.remote.entity.response.CharacterResponse
import com.adrifernandev.rickandmortypedia.feature.character.data.source.remote.entity.response.InfoResponse
import com.adrifernandev.rickandmortypedia.feature.character.data.source.remote.entity.response.LocationResponse
import com.adrifernandev.rickandmortypedia.feature.character.domain.mapper.toDomainModel
import com.adrifernandev.rickandmortypedia.feature.character.domain.model.Character
import com.adrifernandev.rickandmortypedia.feature.character.domain.model.CharacterGender
import com.adrifernandev.rickandmortypedia.feature.character.domain.model.CharacterLocation
import com.adrifernandev.rickandmortypedia.feature.character.domain.model.CharacterStatus
import org.junit.Test
import kotlin.test.assertEquals

class CharacterMapperTest {

    @Test
    fun `toDomainModel should map CharacterResponse to list of Character correctly`() {
        // Given
        val characterResponse = CharacterResponse(
            info = InfoResponse(
                count = 1,
                pages = 1,
                next = null,
                prev = null
            ),
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
                    image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
                    episode = listOf("https://rickandmortyapi.com/api/episode/1"),
                    url = "https://rickandmortyapi.com/api/character/1",
                    created = "2017-11-04T18:48:46.250Z"
                )
            )
        )

        val expected = listOf(
            Character(
                id = 1,
                name = "Rick Sanchez",
                status = CharacterStatus.Alive,
                species = "Human",
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
                image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
                episode = listOf("https://rickandmortyapi.com/api/episode/1"),
                url = "https://rickandmortyapi.com/api/character/1",
            )
        )

        // When
        val result = characterResponse.toDomainModel()

        // Then
        assertEquals(result, expected)

    }
}