package com.adrifernandev.rickandmortypedia.feature.episode.data.source.di

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
    fun providesEpisodeService(
        retrofit: Retrofit
    ): EpisodeService {
        return retrofit.create(EpisodeService::class.java)
    }
}