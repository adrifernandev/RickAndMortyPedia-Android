package com.adrifernandev.rickandmortypedia.feature.character.domain.di

import com.adrifernandev.rickandmortypedia.feature.character.domain.repository.CharacterRepository
import com.adrifernandev.rickandmortypedia.feature.character.domain.usecase.GetPaginatedCharactersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CharacterDomainModule {

    @Provides
    @Singleton
    fun provideGetPaginatedCharactersUseCase(
        charactersRepository: CharacterRepository
    ): GetPaginatedCharactersUseCase {
        return GetPaginatedCharactersUseCase(charactersRepository)
    }
}