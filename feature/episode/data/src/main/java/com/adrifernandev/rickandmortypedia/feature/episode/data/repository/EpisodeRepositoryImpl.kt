package com.adrifernandev.rickandmortypedia.feature.episode.data.repository

import com.adrifernandev.rickandmortypedia.episode.domain.model.Episode
import com.adrifernandev.rickandmortypedia.episode.domain.repository.EpisodeRepository
import com.adrifernandev.rickandmortypedia.feature.episode.data.mapper.toDomain
import com.adrifernandev.rickandmortypedia.feature.episode.data.repository.factory.EpisodeFactory
import javax.inject.Inject

class EpisodeRepositoryImpl @Inject constructor(
    private val episodeFactory: EpisodeFactory
) : EpisodeRepository {

    override suspend fun getEpisodesById(ids: List<Int>): Result<List<Episode>> {
        return episodeFactory.remoteDataStore.getEpisodesByIds(ids)
            .mapCatching { response ->
                response.toDomain()
            }
    }
}