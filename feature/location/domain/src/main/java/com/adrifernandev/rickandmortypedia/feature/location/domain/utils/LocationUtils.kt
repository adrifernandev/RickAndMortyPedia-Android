package com.adrifernandev.rickandmortypedia.feature.location.domain.utils

fun extractLocationIdFromUrl(url: String): Int? {
    return url.split("/").lastOrNull()?.toIntOrNull()
}