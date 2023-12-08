package bangkit.android.androidfundamentalexam12.data.response

import com.google.gson.annotations.SerializedName

data class DetailUser(

	@field:SerializedName("gists_url")
	val gistsUrl: String?,

	@field:SerializedName("repos_url")
	val reposUrl: String?,

	//following
	@field:SerializedName("following_url")
	val followingUrl: String?,

	@field:SerializedName("twitter_username")
	val twitterUsername: Any?,

	@field:SerializedName("bio")
	val bio: Any?,

	@field:SerializedName("created_at")
	val createdAt: String?,

	//Username
	@field:SerializedName("login")
	val login: String?,

	@field:SerializedName("type")
	val type: String?,

	@field:SerializedName("blog")
	val blog: String?,

	@field:SerializedName("subscriptions_url")
	val subscriptionsUrl: String?,

	@field:SerializedName("updated_at")
	val updatedAt: String?,

	@field:SerializedName("site_admin")
	val siteAdmin: Boolean?,

	@field:SerializedName("company")
	val company: String?,

	@field:SerializedName("id")
	val id: Int?,

	@field:SerializedName("public_repos")
	val publicRepos: Int?,

	@field:SerializedName("gravatar_id")
	val gravatarId: String?,

	@field:SerializedName("email")
	val email: Any?,

	@field:SerializedName("organizations_url")
	val organizationsUrl: String?,

	@field:SerializedName("hireable")
	val hireable: Any?,

	@field:SerializedName("starred_url")
	val starredUrl: String?,

	//followers_url
	@field:SerializedName("followers_url")
	val followersUrl: String?,

	@field:SerializedName("public_gists")
	val publicGists: Int?,

	@field:SerializedName("url")
	val url: String?,

	@field:SerializedName("received_events_url")
	val receivedEventsUrl: String?,

	//followersInt
	@field:SerializedName("followers")
	val followers: Int?,

	//avatarUrl
	@field:SerializedName("avatar_url")
	val avatarUrl: String?,

	@field:SerializedName("events_url")
	val eventsUrl: String?,

	@field:SerializedName("html_url")
	val htmlUrl: String?,

	//followingInt
	@field:SerializedName("following")
	val following: Int?,

	//name
	@field:SerializedName("name")
	val name: String?,

	@field:SerializedName("location")
	val location: String?,

	@field:SerializedName("node_id")
	val nodeId: String?
)
