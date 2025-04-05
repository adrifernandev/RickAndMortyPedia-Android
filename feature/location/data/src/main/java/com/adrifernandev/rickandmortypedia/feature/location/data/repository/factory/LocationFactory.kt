package com.adrifernandev.rickandmortypedia.feature.location.data.repository.factory

import com.adrifernandev.rickandmortypedia.common.data.common.DataFactory
import com.adrifernandev.rickandmortypedia.feature.location.data.source.remote.datasource.LocationRemoteDataSource
import javax.inject.Inject

class LocationFactory @Inject constructor(
    override val localDataStore: LocationRemoteDataSource,
    override val remoteDataStore: LocationRemoteDataSource
) : DataFactory<LocationRemoteDataSource, LocationRemoteDataSource>