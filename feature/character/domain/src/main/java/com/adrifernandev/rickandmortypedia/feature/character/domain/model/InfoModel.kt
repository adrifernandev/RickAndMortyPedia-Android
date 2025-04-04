package com.adrifernandev.rickandmortypedia.feature.character.domain.model

data class InfoModel(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?,
)
