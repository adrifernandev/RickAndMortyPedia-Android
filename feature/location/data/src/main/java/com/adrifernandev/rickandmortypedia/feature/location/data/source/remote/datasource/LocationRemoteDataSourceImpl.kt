package com.adrifernandev.rickandmortypedia.feature.location.data.source.remote.datasource

import com.adrifernandev.rickandmortypedia.common.data.network.apiRequestFlow
import com.adrifernandev.rickandmortypedia.feature.location.data.source.remote.entity.response.LocationResponse
import com.adrifernandev.rickandmortypedia.feature.location.data.source.remote.service.LocationService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

private const val IDS_SEPARATOR = ","

class LocationRemoteDataSourceImpl @Inject constructor(
    private val locationService: LocationService
) : LocationRemoteDataSource {

    override fun getLocationsByIds(ids: List<Int>): Flow<Result<List<LocationResponse>>> =
        apiRequestFlow(
            call = { locationService.getLocationsByIds(ids.joinToString(IDS_SEPARATOR)) }
        )
}