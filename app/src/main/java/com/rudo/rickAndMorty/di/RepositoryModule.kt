package com.rudo.rickAndMorty.di

import FavoritesRepositoryImpl
import com.rudo.rickAndMorty.data.dataSource.local.FavoriteLocalDataSource
import com.rudo.rickAndMorty.data.dataSource.remote.RickAndMortyDataSource
import com.rudo.rickAndMorty.data.repository.RickAndMortyRepositoryImpl
import com.rudo.rickAndMorty.domain.FavoritesRepository
import com.rudo.rickAndMorty.domain.RickAndMortyRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    @Singleton
    fun provideRickAndoMortyRepository(
        datasouce: RickAndMortyDataSource
    ): RickAndMortyRepository {
        return RickAndMortyRepositoryImpl(
            dataSource = datasouce
        )
    }

    @Provides
    @Singleton
    fun provideFavoritesRepository(
        datasouce: FavoriteLocalDataSource
    ): FavoritesRepository {
        return FavoritesRepositoryImpl(
            dataSource = datasouce
        )
    }
}