package com.adrifernandev.rickandmortypedia.feature.location.domain.repository

import com.adrifernandev.rickandmortypedia.feature.location.domain.model.Location

interface LocationRepository {
    suspend fun getLocationsById(ids: List<Int>): Result<List<Location>>
}