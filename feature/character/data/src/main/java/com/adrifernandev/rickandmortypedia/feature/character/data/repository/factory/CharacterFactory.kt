package com.adrifernandev.rickandmortypedia.feature.character.data.repository.factory

import com.adrifernandev.rickandmortypedia.common.data.common.DataFactory
import com.adrifernandev.rickandmortypedia.feature.character.data.source.remote.datasource.CharacterRemoteDataSource
import javax.inject.Inject

class CharacterFactory @Inject constructor(
    override val localDataStore: CharacterRemoteDataSource,
    override val remoteDataStore: CharacterRemoteDataSource
) : DataFactory<CharacterRemoteDataSource, CharacterRemoteDataSource> {
}