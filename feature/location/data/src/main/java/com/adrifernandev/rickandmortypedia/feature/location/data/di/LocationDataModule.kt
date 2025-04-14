package com.adrifernandev.rickandmortypedia.feature.location.data.di

import com.adrifernandev.rickandmortypedia.feature.location.data.repository.LocationRepositoryImpl
import com.adrifernandev.rickandmortypedia.feature.location.data.repository.factory.LocationFactory
import com.adrifernandev.rickandmortypedia.feature.location.data.source.remote.datasource.LocationRemoteDataSource
import com.adrifernandev.rickandmortypedia.feature.location.data.source.remote.datasource.LocationRemoteDataSourceImpl
import com.adrifernandev.rickandmortypedia.feature.location.data.source.remote.service.LocationService
import com.adrifernandev.rickandmortypedia.feature.location.domain.repository.LocationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocationDataModule {

    @Provides
    @Singleton
    fun providesLocationService(
        retrofit: Retrofit
    ): LocationService {
        return retrofit.create(LocationService::class.java)
    }

    @Provides
    @Singleton
    fun providesLocationRemoteDataSource(
        locationService: LocationService
    ): LocationRemoteDataSource {
        return LocationRemoteDataSourceImpl(locationService)
    }

    @Provides
    @Singleton
    fun providesLocationRepository(
        locationFactory: LocationFactory
    ): LocationRepository {
        return LocationRepositoryImpl(locationFactory)
    }
}