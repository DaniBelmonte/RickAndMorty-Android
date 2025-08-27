package com.rudo.rickAndMorty.data.dataSource.local

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject



class FavoriteLocalDataSourceImpl @Inject constructor(
    val dataBase: FavoriteRoomDatabase,
): FavoriteLocalDataSource {

    private val dao = dataBase.favoriteDao()

    override suspend fun getAllFavoriteIds(): Flow<List<Int>> {
        return dao.getAllFavoriteIds()
    }

    override suspend fun isFavorite(id: Int): Flow<Boolean> {
        return  dao.isFavoriteFlow(id)
    }

    override suspend fun add(id: Int) = dao.insertFavorite(FavoriteDbo(id))

    override suspend fun remove(id: Int) = dao.removeFavorite(id)

    override suspend fun toggle(id: Int) {
        val isFav = dao.isFavoriteFlow(id).first()
        if (isFav) dao.removeFavorite(id) else dao.insertFavorite(FavoriteDbo(id))
    }
}