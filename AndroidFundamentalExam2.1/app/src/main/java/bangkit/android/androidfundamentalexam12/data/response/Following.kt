package bangkit.android.androidfundamentalexam12.data.response

import com.google.gson.annotations.SerializedName

data class Following(

	@field:SerializedName("avatar_url")
	val avatarUrl: String? = null,

	@field:SerializedName("login")
	val login: String? = null
)
