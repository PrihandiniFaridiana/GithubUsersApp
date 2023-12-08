package bangkit.android.androidfundamentalexam12.data.retrofit

import bangkit.android.androidfundamentalexam12.data.response.DetailUser
import bangkit.android.androidfundamentalexam12.data.response.Follower
import bangkit.android.androidfundamentalexam12.data.response.Following
import bangkit.android.androidfundamentalexam12.data.response.ResponseItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("search/users")
    fun getItems(
        @Query("q") q: String
    ): Call<ResponseItem>

    @GET("users/{username}")
    fun getDetailUser(
        @Path("username") username: String
    ): Call<DetailUser>

    @GET("users/{username}/followers")
    fun getFollowers(
        @Path("username") username: String
    ): Call<List<Follower>>

    @GET("users/{username}/following")
    fun getFollowing(
        @Path("username") username: String
    ): Call<List<Following>>
}