package com.adrifernandev.rickandmortypedia.feature.location.domain.model

import androidx.compose.runtime.Stable

@Stable
data class Location(
    val id: Int,
    val name: String,
    val type: String,
    val dimension: String,
    val residents: List<String>,
    val url: String
)
