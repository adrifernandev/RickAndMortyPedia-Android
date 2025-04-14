package com.adrifernandev.rickandmortypedia.feature.episode.data.source.remote.datasource

import com.adrifernandev.rickandmortypedia.feature.episode.data.source.remote.entity.response.EpisodeResponse

interface EpisodeRemoteDataSource {

    suspend fun getEpisodesByIds(ids: List<Int>): Result<List<EpisodeResponse>>
}