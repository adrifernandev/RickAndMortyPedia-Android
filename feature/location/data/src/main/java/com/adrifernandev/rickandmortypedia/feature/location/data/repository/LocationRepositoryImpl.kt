package com.adrifernandev.rickandmortypedia.feature.location.data.repository

import com.adrifernandev.rickandmortypedia.feature.location.data.mapper.toDomainModel
import com.adrifernandev.rickandmortypedia.feature.location.data.repository.factory.LocationFactory
import com.adrifernandev.rickandmortypedia.feature.location.domain.model.Location
import com.adrifernandev.rickandmortypedia.feature.location.domain.repository.LocationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(
    private val locationFactory: LocationFactory
) : LocationRepository {

    override fun getLocationsById(ids: List<Int>): Flow<Result<List<Location>>> {
        return locationFactory.remoteDataStore.getLocationsByIds(ids)
            .map { response ->
                response.mapCatching {
                    it.toDomainModel()
                }
            }
    }
}