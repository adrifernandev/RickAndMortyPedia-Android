package com.adrifernandev.rickandmortypedia.feature.location.domain.usecase

import com.adrifernandev.rickandmortypedia.feature.location.domain.model.Location
import com.adrifernandev.rickandmortypedia.feature.location.domain.repository.LocationRepository
import javax.inject.Inject

class GetLocationsByIdsUseCase @Inject constructor(
    private val locationRepository: LocationRepository
) {
    suspend operator fun invoke(ids: List<Int>): Result<List<Location>> =
        locationRepository.getLocationsById(ids)
}