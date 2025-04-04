package com.adrifernandev.rickandmortypedia.feature.character.domain.model

data class PagedCharacters(
    val info: InfoModel,
    val results: List<Character>,
)
