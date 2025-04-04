package com.adrifernandev.rickandmortypedia.feature.character.data.mapper

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

fun CharacterResponse.toDomainModel(): PagedCharacters {
    return PagedCharacters(
        info = info.toDomainModel(),
        results = results.map { it.toDomainModel() }
    )
}

fun InfoResponse.toDomainModel(): InfoModel {
    return InfoModel(
        count = count,
        pages = pages,
        next = next,
        prev = prev
    )
}

fun CharacterItemResponse.toDomainModel(): Character {
    return Character(
        id = id,
        name = name,
        status = status.toStatus(),
        species = species,
        type = type,
        gender = gender.toGender(),
        origin = origin.toDomainModel(),
        location = location.toDomainModel(),
        image = image,
        episode = episode,
        url = url
    )
}

fun LocationResponse.toDomainModel(): CharacterLocation {
    return CharacterLocation(
        name = name,
        url = url
    )
}

private fun String.toStatus(): CharacterStatus {
    return when (this) {
        "Alive" -> CharacterStatus.Alive
        "Dead" -> CharacterStatus.Dead
        else -> CharacterStatus.Unknown
    }
}

private fun String.toGender(): CharacterGender {
    return when (this) {
        "Male" -> CharacterGender.Male
        "Female" -> CharacterGender.Female
        "Genderless" -> CharacterGender.Genderless
        else -> CharacterGender.Unknown
    }
}
