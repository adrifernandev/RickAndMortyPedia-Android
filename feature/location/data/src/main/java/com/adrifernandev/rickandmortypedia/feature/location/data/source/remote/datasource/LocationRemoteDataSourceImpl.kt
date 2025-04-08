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
        apiRequestCall(
            call = { locationService.getLocationsByIds(ids.joinToString(IDS_SEPARATOR)) }
        )
}