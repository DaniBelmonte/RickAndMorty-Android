package com.rudo.rickAndMorty.data.dataSource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFavorite(entity: FavoriteDbo)

    @Query("DELETE FROM favorites WHERE characterId = :id")
    suspend fun removeFavorite(id: Int)

    @Query("SELECT EXISTS(SELECT 1 FROM favorites WHERE characterId = :id)")
    fun isFavoriteFlow(id: Int): Flow<Boolean>

    @Query("SELECT characterId FROM favorites")
    fun getAllFavoriteIds(): Flow<List<Int>>
}