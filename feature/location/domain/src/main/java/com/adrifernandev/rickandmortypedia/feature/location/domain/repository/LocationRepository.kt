package com.adrifernandev.rickandmortypedia.feature.location.domain.repository

import com.adrifernandev.rickandmortypedia.feature.location.domain.model.Location
import kotlinx.coroutines.flow.Flow

interface LocationRepository {
    fun getLocationsById(ids: List<Int>): Flow<Result<List<Location>>>
}