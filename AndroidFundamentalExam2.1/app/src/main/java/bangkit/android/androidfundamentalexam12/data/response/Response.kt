package bangkit.android.androidfundamentalexam12.data.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ResponseItem(

	@field:SerializedName("total_count")
	val totalCount: Int?,

	@field:SerializedName("incomplete_results")
	val incompleteResults: Boolean? ,

	@field:SerializedName("items")
	val items: List<ItemsItem>
)

data class ItemsItem(

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("avatar_url")
	val avatarUrl: String?,

	@field:SerializedName("login")
	val login: String?

): Serializable
