package com.rudo.rickAndMorty.di

import FavoritesRepositoryImpl
import com.rudo.rickAndMorty.data.dataSource.local.FavoriteLocalDataSource
import com.rudo.rickAndMorty.data.dataSource.local.FavoriteLocalDataSourceImpl
import com.rudo.rickAndMorty.data.dataSource.local.FavoriteRoomDatabase
import com.rudo.rickAndMorty.data.dataSource.remote.RickAndMortyDataSource
import com.rudo.rickAndMorty.data.dataSource.remote.RickAndMortyDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {
    @Provides
    @Singleton
    fun provideRickAndMortyDataSource():
        RickAndMortyDataSource {
        return RickAndMortyDataSourceImpl()
    }

    @Provides
    @Singleton
    fun provideFavoritesDataSource(
        dataBase: FavoriteRoomDatabase
    ):
        FavoriteLocalDataSource {
        return FavoriteLocalDataSourceImpl(
            dataBase = dataBase
        )
    }
}