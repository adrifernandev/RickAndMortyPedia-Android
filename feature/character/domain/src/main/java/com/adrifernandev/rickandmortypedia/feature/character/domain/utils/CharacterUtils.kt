package com.adrifernandev.rickandmortypedia.feature.character.domain.utils

import com.adrifernandev.rickandmortypedia.feature.character.domain.model.Character

fun extractEpisodesIdsFromCharacter(
    character: Character
): List<Int> {
    return character.episodes.map { it.substringAfterLast("/").toInt() }
}