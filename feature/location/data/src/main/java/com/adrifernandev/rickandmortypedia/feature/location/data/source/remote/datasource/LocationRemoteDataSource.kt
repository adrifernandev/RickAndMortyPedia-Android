package com.adrifernandev.rickandmortypedia.feature.location.data.source.remote.datasource

import com.adrifernandev.rickandmortypedia.feature.location.data.source.remote.entity.response.LocationResponse
import kotlinx.coroutines.flow.Flow

interface LocationRemoteDataSource {

    fun getLocationsByIds(
        ids: List<Int>
    ): Flow<Result<List<LocationResponse>>>
}
