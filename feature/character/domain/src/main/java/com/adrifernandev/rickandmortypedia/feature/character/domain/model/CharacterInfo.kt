package com.adrifernandev.rickandmortypedia.feature.character.domain.model

import androidx.compose.runtime.Stable

@Stable
data class CharacterInfo(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
)
