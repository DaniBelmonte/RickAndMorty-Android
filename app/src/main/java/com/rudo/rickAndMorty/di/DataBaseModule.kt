package com.rudo.rickAndMorty.di

import android.content.Context
import androidx.room.Room.databaseBuilder
import com.rudo.rickAndMorty.data.dataSource.local.FavoriteRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    @Provides
    @Singleton
    fun provideFavoriteDataBase(@ApplicationContext context: Context): FavoriteRoomDatabase {
        return databaseBuilder(
            context.applicationContext,
            FavoriteRoomDatabase::class.java,
            "favorite_database"
        )
            .fallbackToDestructiveMigration(true)
            .build()
    }
}