package com.adrifernandev.rickandmortypedia.feature.episode.data.di

import com.adrifernandev.rickandmortypedia.episode.domain.repository.EpisodeRepository
import com.adrifernandev.rickandmortypedia.feature.episode.data.repository.EpisodeRepositoryImpl
import com.adrifernandev.rickandmortypedia.feature.episode.data.repository.factory.EpisodeFactory
import com.adrifernandev.rickandmortypedia.feature.episode.data.source.remote.datasource.EpisodeRemoteDataSource
import com.adrifernandev.rickandmortypedia.feature.episode.data.source.remote.datasource.EpisodeRemoteDataSourceImpl
import com.adrifernandev.rickandmortypedia.feature.episode.data.source.remote.service.EpisodeService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object EpisodeDataModule {

    @Provides
    @Singleton
    fun providesEpisodeRepository(
        episodeFactory: EpisodeFactory
    ): EpisodeRepository {
        return EpisodeRepositoryImpl(episodeFactory)
    }

    @Provides
    @Singleton
    fun providesEpisodeService(
        retrofit: Retrofit
    ): EpisodeService {
        return retrofit.create(EpisodeService::class.java)
    }

    @Provides
    @Singleton
    fun providesEpisodeRemoteDataSource(
        episodeService: EpisodeService
    ): EpisodeRemoteDataSource {
        return EpisodeRemoteDataSourceImpl(episodeService)
    }
}