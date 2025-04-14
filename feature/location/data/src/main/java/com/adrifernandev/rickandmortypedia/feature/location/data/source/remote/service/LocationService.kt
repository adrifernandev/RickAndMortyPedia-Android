package com.adrifernandev.rickandmortypedia.feature.location.data.source.remote.service

import com.adrifernandev.rickandmortypedia.feature.location.data.source.remote.entity.response.LocationResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface LocationService {

    companion object {
        private const val IDS = "ids"
        private const val ID = "id"
        private const val LOCATIONS_PATH = "location/{$IDS}"
        private const val LOCATION_PATH = "location/{$ID}"
    }

    @GET(LOCATIONS_PATH)
    suspend fun getLocationsByIds(
        @Path(IDS) ids: String
    ): Response<List<LocationResponse>>

    @GET(LOCATION_PATH)
    suspend fun getLocationById(
        @Path(ID) id: Int
    ): Response<LocationResponse>
}