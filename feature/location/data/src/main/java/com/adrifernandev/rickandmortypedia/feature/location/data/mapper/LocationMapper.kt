package com.adrifernandev.rickandmortypedia.feature.location.data.mapper

import com.adrifernandev.rickandmortypedia.feature.location.data.source.remote.entity.response.LocationResponse
import com.adrifernandev.rickandmortypedia.feature.location.domain.model.Location

fun List<LocationResponse>.toDomainModel() = map {
    it.toDomainModel()
}

fun LocationResponse.toDomainModel() = Location(
    id = this.id,
    name = this.name,
    type = this.type,
    dimension = this.dimension,
    residents = this.residents,
    url = this.url
)