package bangkit.android.androidfundamentalexam12.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import bangkit.android.androidfundamentalexam12.data.response.Follower
import bangkit.android.androidfundamentalexam12.data.response.Following
import bangkit.android.androidfundamentalexam12.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowerViewModel: ViewModel() {

    private val _follower = MutableLiveData<List<Follower>>()
    val follower: LiveData<List<Follower>> = _follower

    companion object{
        private const val TAG = "FollowerViewModel"
    }

    fun setListFollower(username: String){
        val client = ApiConfig.getApiService().getFollowers(username)
        client.enqueue(object : Callback<List<Follower>>{
            override fun onResponse(call: Call<List<Follower>>, response: Response<List<Follower>>) {
                if (response.isSuccessful){
                    val responseBody = response.body()
                    _follower.postValue(responseBody)
                } else {
                    Log.e(TAG, "onFailure")
                }
            }

            override fun onFailure(call: Call<List<Follower>>, t: Throwable) {
                Log.e(TAG, "onFailure")
            }
        })
    }
}

class FollowingViewModel: ViewModel() {

    private val _following = MutableLiveData<List<Following>>()
    val following: LiveData<List<Following>> = _following

    companion object{
        private const val TAG = "FollowingViewModel"
    }

    fun setListFollowing(username: String){
        val client = ApiConfig.getApiService().getFollowing(username)
        client.enqueue(object : Callback<List<Following>>{
            override fun onResponse(call: Call<List<Following>>, response: Response<List<Following>>) {
                if (response.isSuccessful){
                    val responseBody = response.body()
                    _following.value = responseBody
                } else {
                    Log.e(TAG, "onFailure")
                }
            }

            override fun onFailure(call: Call<List<Following>>, t: Throwable) {
                Log.e(TAG, "onFailure")
            }
        })
    }
}