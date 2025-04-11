package com.adrifernandev.rickandmortypedia.feature.episode.data.source.remote.datasource

import com.adrifernandev.rickandmortypedia.common.data.network.apiRequestCall
import com.adrifernandev.rickandmortypedia.feature.episode.data.source.remote.entity.response.EpisodeResponse
import com.adrifernandev.rickandmortypedia.feature.episode.data.source.remote.service.EpisodeService
import javax.inject.Inject

private const val IDS_SEPARATOR = ","

class EpisodeRemoteDataSourceImpl @Inject constructor(
    private val episodeService: EpisodeService
) : EpisodeRemoteDataSource {

    override suspend fun getEpisodesByIds(ids: List<Int>): Result<List<EpisodeResponse>> =
        apiRequestCall { episodeService.getEpisodesById(ids.joinToString(IDS_SEPARATOR)) }
}