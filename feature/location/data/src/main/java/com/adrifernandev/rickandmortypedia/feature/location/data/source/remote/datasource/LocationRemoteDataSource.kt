package com.adrifernandev.rickandmortypedia.feature.location.data.source.remote.datasource

import com.adrifernandev.rickandmortypedia.feature.location.data.source.remote.entity.response.LocationResponse

interface LocationRemoteDataSource {

    suspend fun getLocationsByIds(
        ids: List<Int>
    ): Result<List<LocationResponse>>
}
