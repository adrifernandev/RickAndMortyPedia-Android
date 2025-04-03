package com.adrifernandev.rickandmortypedia.feature.character.domain

import com.adrifernandev.rickandmortypedia.feature.character.domain.repository.CharacterRepository
import com.adrifernandev.rickandmortypedia.feature.character.domain.usecase.GetAllCharactersUseCase
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
    fun provideGetAllCharactersUseCase(
        charactersRepository: CharacterRepository
    ): GetAllCharactersUseCase {
        return GetAllCharactersUseCase(charactersRepository)
    }
}