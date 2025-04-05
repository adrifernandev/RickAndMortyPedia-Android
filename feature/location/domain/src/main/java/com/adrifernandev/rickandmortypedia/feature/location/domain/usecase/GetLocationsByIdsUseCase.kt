package com.adrifernandev.rickandmortypedia.feature.location.domain.usecase

import com.adrifernandev.rickandmortypedia.feature.location.domain.model.Location
import com.adrifernandev.rickandmortypedia.feature.location.domain.repository.LocationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLocationsByIdsUseCase @Inject constructor(
    private val locationRepository: LocationRepository
) {
    operator fun invoke(ids: List<Int>): Flow<Result<List<Location>>> =
        locationRepository.getLocationsById(ids)
}