package com.adrifernandev.rickandmortypedia.episode.domain.di

import com.adrifernandev.rickandmortypedia.episode.domain.repository.EpisodeRepository
import com.adrifernandev.rickandmortypedia.episode.domain.usecase.GetEpisodesByIdsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object EpisodeDomainModule {

    @Provides
    @Singleton
    fun provideGetEpisodesByIdsUseCase(
        episodeRepository: EpisodeRepository
    ): GetEpisodesByIdsUseCase {
        return GetEpisodesByIdsUseCase(
            episodeRepository
        )
    }
}