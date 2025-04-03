package com.adrifernandev.rickandmortypedia.feature.character.domain.model

import androidx.compose.runtime.Stable

@Stable
data class CharacterLocation(
    val name: String,
    val url: String
)
