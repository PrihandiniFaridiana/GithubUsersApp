package bangkit.android.androidfundamentalexam12.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import bangkit.android.androidfundamentalexam12.data.response.ItemsItem
import bangkit.android.androidfundamentalexam12.data.response.ResponseItem
import bangkit.android.androidfundamentalexam12.data.retrofit.ApiConfig
import retrofit2.Response

class MainViewModel: ViewModel() {

    private val _items = MutableLiveData<List<ItemsItem>>()
    val items: LiveData<List<ItemsItem>> = _items

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    companion object{
        const val TAG = "MainViewModel"
        var USER = "Prihan"
    }

    fun setUser(userInput: String){
        USER = userInput
    }

    init {
        getListItems()
    }

    fun getListItems(){
        _isLoading.value = true
        val client = ApiConfig.getApiService().getItems(USER)
        client.enqueue(object : Callback<ResponseItem> {
            override fun onResponse(call: Call<ResponseItem>, response: Response<ResponseItem>) {
                _isLoading.value = false
                if (response.isSuccessful){
                    val responseBody = response.body()
                    _items.value = responseBody?.items
                } else {
                    Log.e(TAG, "onFailure")
                }
            }

            override fun onFailure(call: Call<ResponseItem>, t: Throwable) {
                Log.e(TAG, "onFailure")
            }
        })
    }
}