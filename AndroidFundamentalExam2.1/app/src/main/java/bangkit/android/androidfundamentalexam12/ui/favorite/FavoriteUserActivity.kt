package bangkit.android.androidfundamentalexam12.ui.favorite

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import bangkit.android.androidfundamentalexam12.data.local.FavoriteUser
import bangkit.android.androidfundamentalexam12.databinding.ActivityFavoriteUserBinding
import bangkit.android.androidfundamentalexam12.ui.detail.DetailActivity

class FavoriteUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteUserBinding
    private lateinit var adapter: FavoriteUserAdapter
    private lateinit var favoriteUserViewModel: FavoriteUserViewModel

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = FavoriteUserAdapter()
        adapter.notifyDataSetChanged()

        favoriteUserViewModel = ViewModelProvider(this)[FavoriteUserViewModel::class.java]

        adapter.setOnItemClickCallback(object : FavoriteUserAdapter.OnItemClickCallback {
            override fun onItemClick(data: FavoriteUser) {
                Intent(this@FavoriteUserActivity, DetailActivity::class.java).also {
                    it.putExtra(DetailActivity.USERNAME, data.username)
                    it.putExtra(DetailActivity.ID, data.id)
                    it.putExtra(DetailActivity.AVATAR_URL, data.avatar_url)
                    startActivity(it)
                }
            }
        })

        binding.apply {
            rvListFavoriteUser.setHasFixedSize(true)
            rvListFavoriteUser.layoutManager = LinearLayoutManager(this@FavoriteUserActivity)
            rvListFavoriteUser.adapter = adapter
        }

        favoriteUserViewModel.getAllFavoriteUser()?.observe(this, {
            if (it != null){
                val listFavoriteUser = mapListFavoriteUser(it)
                adapter.setListFavoriteUser(listFavoriteUser)
            }
        })
    }

    private fun mapListFavoriteUser(listFavoriteUser: List<FavoriteUser>): ArrayList<FavoriteUser> {
        val listFavoriteUsers = ArrayList<FavoriteUser>()
        for (user in listFavoriteUser) {
            val userMapped = FavoriteUser(
                user.id,
                user.username,
                user.avatar_url
            )
            listFavoriteUsers.add(userMapped)
        }
        return listFavoriteUsers
    }

}