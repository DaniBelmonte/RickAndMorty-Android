package com.rudo.rickAndMorty.data.dataSource.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
@Database(
    entities = [FavoriteDbo::class],
    version = 1,
    exportSchema = false
)
abstract class FavoriteRoomDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
}