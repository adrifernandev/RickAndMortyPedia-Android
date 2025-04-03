package com.adrifernandev.rickandmortypedia.feature.character.data.di

import com.adrifernandev.rickandmortypedia.feature.character.data.source.remote.datasource.CharacterRemoteDataSource
import com.adrifernandev.rickandmortypedia.feature.character.data.source.remote.datasource.CharacterRemoteDataSourceImpl
import com.adrifernandev.rickandmortypedia.feature.character.data.source.remote.service.CharacterService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CharacterDataModule {

    @Provides
    @Singleton
    fun provideCharacterService(
        retrofit: Retrofit
    ): CharacterService {
        return retrofit.create(CharacterService::class.java)
    }

    @Provides
    @Singleton
    fun provideCharacterRemoteDataSource(
        characterService: CharacterService
    ): CharacterRemoteDataSource {
        return CharacterRemoteDataSourceImpl(characterService)
    }
}