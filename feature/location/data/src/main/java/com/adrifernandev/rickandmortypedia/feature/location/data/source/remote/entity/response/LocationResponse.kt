package com.adrifernandev.rickandmortypedia.feature.location.data.source.remote.entity.response

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class LocationResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("dimension")
    val dimension: String,
    @SerializedName("residents")
    val residents: List<String>,
    @SerializedName("url")
    val url: String,
    @SerializedName("created")
    val created: String
)
