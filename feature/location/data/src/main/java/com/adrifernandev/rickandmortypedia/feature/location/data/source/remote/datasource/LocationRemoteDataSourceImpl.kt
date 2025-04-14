package com.adrifernandev.rickandmortypedia.feature.location.data.source.remote.datasource

import com.adrifernandev.rickandmortypedia.common.data.network.apiRequestCall
import com.adrifernandev.rickandmortypedia.feature.location.data.source.remote.entity.response.LocationResponse
import com.adrifernandev.rickandmortypedia.feature.location.data.source.remote.service.LocationService
import javax.inject.Inject

private const val IDS_SEPARATOR = ","

class LocationRemoteDataSourceImpl @Inject constructor(
    private val locationService: LocationService
) : LocationRemoteDataSource {

    override suspend fun getLocationsByIds(ids: List<Int>): Result<List<LocationResponse>> =
        if (ids.size == 1) {
            getSingleLocationById(ids.first()).map { listOf(it) }
        } else {
            getMultipleLocationsByIds(ids)
        }

    private suspend fun getSingleLocationById(id: Int): Result<LocationResponse> =
        apiRequestCall(
            call = { locationService.getLocationById(id) }
        )

    private suspend fun getMultipleLocationsByIds(ids: List<Int>): Result<List<LocationResponse>> =
        apiRequestCall(
            call = { locationService.getLocationsByIds(ids.joinToString(IDS_SEPARATOR)) }
        )
}