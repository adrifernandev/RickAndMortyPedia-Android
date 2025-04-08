package com.adrifernandev.rickandmortypedia.feature.character.domain.repository

import com.adrifernandev.rickandmortypedia.feature.character.domain.model.PagedCharacters

interface CharacterRepository {

    suspend fun getCharacters(page: Int): Result<PagedCharacters?>
}