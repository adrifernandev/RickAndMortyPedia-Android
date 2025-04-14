package com.adrifernandev.rickandmortypedia.feature.location.domain.di

import com.adrifernandev.rickandmortypedia.feature.location.domain.repository.LocationRepository
import com.adrifernandev.rickandmortypedia.feature.location.domain.usecase.GetLocationsByIdsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocationDomainModule {

    @Provides
    @Singleton
    fun providesGetLocationsByIdsUseCase(
        locationRepository: LocationRepository
    ): GetLocationsByIdsUseCase {
        return GetLocationsByIdsUseCase(
            locationRepository
        )
    }
}