package bangkit.android.androidfundamentalexam12.data.response

import com.google.gson.annotations.SerializedName

data class Follower(

	@field:SerializedName("avatar_url")
	val avatarUrl: String?,

	@field:SerializedName("login")
	val login: String?
)
