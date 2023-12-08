package bangkit.android.androidfundamentalexam12.ui.favorite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import bangkit.android.androidfundamentalexam12.data.local.FavoriteUser
import bangkit.android.androidfundamentalexam12.data.local.FavoriteUserDao
import bangkit.android.androidfundamentalexam12.data.local.FavoriteUserRoomDatabase

class FavoriteUserViewModel(application: Application): AndroidViewModel(application) {

    private var mfavoriteUserDao: FavoriteUserDao?
    private var mfavoriteUserRoomDatabase: FavoriteUserRoomDatabase? = FavoriteUserRoomDatabase.getDatabase(application)

    init {
        mfavoriteUserDao = mfavoriteUserRoomDatabase?.favoriteUserDAO()
    }

    fun getAllFavoriteUser(): LiveData<List<FavoriteUser>>? {
        return mfavoriteUserDao?.getAllFavoriteUser()
    }

}