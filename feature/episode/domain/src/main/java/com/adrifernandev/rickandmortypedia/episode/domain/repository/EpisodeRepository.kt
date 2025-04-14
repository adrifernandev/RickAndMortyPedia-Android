package com.adrifernandev.rickandmortypedia.episode.domain.repository

import com.adrifernandev.rickandmortypedia.episode.domain.model.Episode

interface EpisodeRepository {
    suspend fun getEpisodesById(ids: List<Int>): Result<List<Episode>>
}