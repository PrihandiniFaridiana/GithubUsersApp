package bangkit.android.androidfundamentalexam12.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavoriteUserDao {

    @Insert
    suspend fun addFavoriteUser(favoriteUser: FavoriteUser)

    @Query("SELECT * FROM favorite")
    fun getAllFavoriteUser(): LiveData<List<FavoriteUser>>

    @Query("SELECT count(*) FROM favorite WHERE favorite.id = :id")
    suspend fun checkFavoriteUser(id: Int): Int

    @Query("DELETE FROM favorite WHERE favorite.id = :id")
    suspend fun deleteFavoriteUser(id: Int): Int

}