package com.adrifernandev.rickandmortypedia.feature.episode.data.repository.factory

import com.adrifernandev.rickandmortypedia.common.data.common.DataFactory
import com.adrifernandev.rickandmortypedia.feature.episode.data.source.remote.datasource.EpisodeRemoteDataSource
import javax.inject.Inject

class EpisodeFactory @Inject constructor(
    override val localDataStore: EpisodeRemoteDataSource,
    override val remoteDataStore: EpisodeRemoteDataSource
) : DataFactory<EpisodeRemoteDataSource, EpisodeRemoteDataSource>