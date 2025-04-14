package com.adrifernandev.rickandmortypedia.episode.domain.usecase

import com.adrifernandev.rickandmortypedia.episode.domain.model.Episode
import com.adrifernandev.rickandmortypedia.episode.domain.repository.EpisodeRepository
import javax.inject.Inject

class GetEpisodesByIdsUseCase @Inject constructor(
    private val episodeRepository: EpisodeRepository
) {
    suspend operator fun invoke(ids: List<Int>): Result<List<Episode>> =
        episodeRepository.getEpisodesById(ids)
}