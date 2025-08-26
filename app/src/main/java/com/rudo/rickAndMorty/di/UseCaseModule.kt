package com.rudo.rickAndMorty.di

import com.rudo.rickAndMorty.domain.FavoritesRepository
import com.rudo.rickAndMorty.domain.RickAndMortyRepository
import com.rudo.rickAndMorty.domain.useCase.FavoritesUseCase
import com.rudo.rickAndMorty.domain.useCase.FavoritesUseCaseImpl
import com.rudo.rickAndMorty.domain.useCase.RickAndMortyUseCase
import com.rudo.rickAndMorty.domain.useCase.RickAndMortyUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
    @Provides
    @Singleton
    fun provideRickAndMortyUseCase(
        repository: RickAndMortyRepository
    ): RickAndMortyUseCase {
        return RickAndMortyUseCaseImpl(
            repository = repository
        )
    }

    @Provides
    @Singleton
    fun provideFavoritesUseCase(
        repository: FavoritesRepository
    ): FavoritesUseCase {
        return FavoritesUseCaseImpl(
            repository = repository
        )
    }
}