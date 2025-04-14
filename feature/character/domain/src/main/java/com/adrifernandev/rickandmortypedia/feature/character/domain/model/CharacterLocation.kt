package com.adrifernandev.rickandmortypedia.feature.character.domain.model

import androidx.compose.runtime.Stable
import kotlinx.serialization.Serializable

@Stable
@Serializable
data class CharacterLocation(
    val name: String,
    val url: String
)
