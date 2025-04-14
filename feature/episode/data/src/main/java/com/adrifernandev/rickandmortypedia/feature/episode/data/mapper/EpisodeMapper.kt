package com.adrifernandev.rickandmortypedia.feature.episode.data.mapper

import com.adrifernandev.rickandmortypedia.episode.domain.model.Episode
import com.adrifernandev.rickandmortypedia.feature.episode.data.source.remote.entity.response.EpisodeResponse
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun List<EpisodeResponse>.toDomain() = map {
    it.toDomain()
}

fun EpisodeResponse.toDomain() = Episode(
    id = id,
    name = name,
    airDate = parseAirDateToDate(airDate),
    episode = extractEpisodeNumber(seasonAndEpisode),
    season = extractSeasonNumber(seasonAndEpisode),
    characters = characters
)

private fun parseAirDateToDate(dateAsString: String): Date? {
    return try {
        val formatter = SimpleDateFormat("MMMM dd, yyyy", Locale.ENGLISH)
        formatter.parse(dateAsString)
    } catch (e: Exception) {
        null
    }
}

private fun extractSeasonNumber(code: String): Int {
    return Regex("""S(\d{2})E\d{2}""")
        .find(code)
        ?.groups?.get(1)
        ?.value
        ?.toIntOrNull() ?: 0
}

private fun extractEpisodeNumber(code: String): Int {
    return Regex("""S\d{2}E(\d{2})""")
        .find(code)
        ?.groups?.get(1)
        ?.value
        ?.toIntOrNull() ?: 0
}