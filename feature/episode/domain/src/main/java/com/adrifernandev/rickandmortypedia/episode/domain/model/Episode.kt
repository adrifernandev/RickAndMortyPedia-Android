package com.adrifernandev.rickandmortypedia.episode.domain.model

import androidx.compose.runtime.Stable
import java.util.Date

@Stable
data class Episode(
    val id: Int,
    val name: String,
    val airDate: Date,
    val episode: Int,
    val season: Int,
    val characters: List<String>,
)
