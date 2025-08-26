package com.rudo.rickAndMorty.data.dataSource.local

// data/local/FavoriteEntity.kt
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class FavoriteDbo(
    @PrimaryKey val characterId: Int
)