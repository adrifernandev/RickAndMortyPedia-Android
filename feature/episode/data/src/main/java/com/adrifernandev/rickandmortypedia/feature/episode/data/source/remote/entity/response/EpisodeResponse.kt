package com.adrifernandev.rickandmortypedia.feature.episode.data.source.remote.entity.response

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class EpisodeResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("air_date")
    val airDate: String,
    @SerializedName("episode")
    val episode: String,
    @SerializedName("characters")
    val characters: List<String>
)