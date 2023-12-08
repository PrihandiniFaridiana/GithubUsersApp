package bangkit.android.androidfundamentalexam12.ui.detail

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import bangkit.android.androidfundamentalexam12.data.local.FavoriteUser
import bangkit.android.androidfundamentalexam12.data.local.FavoriteUserDao
import bangkit.android.androidfundamentalexam12.data.local.FavoriteUserRoomDatabase
import bangkit.android.androidfundamentalexam12.data.response.DetailUser
import bangkit.android.androidfundamentalexam12.data.retrofit.ApiConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

class DetailViewModel(application: Application): AndroidViewModel(application) {

    private var mFavoriteUserDao: FavoriteUserDao?
    private var mFavoriteUserRoomDatabase: FavoriteUserRoomDatabase? = FavoriteUserRoomDatabase.getDatabase(application)

    init {
        mFavoriteUserDao = mFavoriteUserRoomDatabase?.favoriteUserDAO()
    }

    private val _userDetail = MutableLiveData<DetailUser?>()
    val userDetail: LiveData<DetailUser?> = _userDetail

    companion object{
        private const val TAG = "DetailViewModel"
    }

    fun setDetailUser(username: String){
        val client = ApiConfig.getApiService().getDetailUser(username)
        client.enqueue(object : Callback<DetailUser> {
            override fun onResponse(call: Call<DetailUser>, response: Response<DetailUser>){
                if (response.isSuccessful){
                    val responseBody = response.body()
                    _userDetail.postValue(responseBody)
                } else {
                    Log.e(TAG, "onFailure")
                }
            }

            override fun onFailure(call: Call<DetailUser>, t: Throwable){
                Log.e(TAG, "onFailure")
            }
        })
    }

    fun addFavoriteUser(id: Int, username: String, avatarUrl: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val user = FavoriteUser(id, username, avatarUrl)
            mFavoriteUserDao?.addFavoriteUser(user)
        }
    }

    suspend fun checkFavoriteUser(id: Int) = mFavoriteUserDao?.checkFavoriteUser(id)

    fun deleteFavoriteUser(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            mFavoriteUserDao?.deleteFavoriteUser(id)
        }
    }

}