package com.adrifernandev.rickandmortypedia.feature.character.data.source.remote.entity.response

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class CharacterResponse(
    @SerializedName("info")
    val info: InfoResponse,
    @SerializedName("results")
    val results: List<CharacterItemResponse>
)

@Keep
data class InfoResponse(
    @SerializedName("count")
    val count: Int,
    @SerializedName("pages")
    val pages: Int,
    @SerializedName("next")
    val next: String?,
    @SerializedName("prev")
    val prev: String?
)

@Keep
data class CharacterItemResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("species")
    val species: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("origin")
    val origin: LocationResponse,
    @SerializedName("location")
    val location: LocationResponse,
    @SerializedName("image")
    val image: String,
    @SerializedName("episode")
    val episode: List<String>,
    @SerializedName("url")
    val url: String,
    @SerializedName("created")
    val created: String
)

@Keep
data class LocationResponse(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)
