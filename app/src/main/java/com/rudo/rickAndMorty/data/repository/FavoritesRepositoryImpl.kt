// data/repository/FavoritesRepositoryImpl.kt
import com.rudo.rickAndMorty.data.dataSource.local.FavoriteLocalDataSource
import com.rudo.rickAndMorty.domain.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class FavoritesRepositoryImpl @Inject constructor(
    private val dataSource: FavoriteLocalDataSource
) : FavoritesRepository {
    override suspend fun getAllFavoriteIds(): Flow<List<Int>> {
       return dataSource.getAllFavoriteIds()
    }

    override suspend fun isFavorite(id: Int): Flow<Boolean> = dataSource.isFavorite(id)

    override suspend fun add(id: Int) = dataSource.add((id))

    override suspend fun remove(id: Int) = dataSource.remove(id)

    override suspend fun toggle(id: Int) {
        val isFav = dataSource.isFavorite(id).first()
        if (isFav) {
            dataSource.remove(id)
        } else {
            dataSource.add((id))
        }
    }
}