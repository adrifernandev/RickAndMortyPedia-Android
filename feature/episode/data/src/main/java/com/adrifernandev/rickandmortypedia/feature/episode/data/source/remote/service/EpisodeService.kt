package com.adrifernandev.rickandmortypedia.feature.episode.data.source.remote.service

import com.adrifernandev.rickandmortypedia.feature.episode.data.source.remote.entity.response.EpisodeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface EpisodeService {

    companion object {
        private const val IDS = "ids"
        private const val ID = "id"
        private const val EPISODE_PATH = "episode/{$ID}"
        private const val EPISODES_PATH = "episode/{$IDS}"
    }

    @GET(EPISODES_PATH)
    suspend fun getEpisodesById(
        @Path(IDS) ids: String
    ): Response<List<EpisodeResponse>>

    @GET(EPISODE_PATH)
    suspend fun getEpisodeById(
        @Path(ID) id: Int
    ): Response<EpisodeResponse>
}