package bangkit.android.androidfundamentalexam12.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import bangkit.android.androidfundamentalexam12.R
import bangkit.android.androidfundamentalexam12.databinding.ActivityDetailBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    private lateinit var detailViewModel: DetailViewModel

    companion object{
        const val USERNAME = "username"
        const val ID = "id"
        const val AVATAR_URL = "avatar_url"
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_1,
            R.string.tab_2
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        detailViewModel = ViewModelProvider(this)[DetailViewModel::class.java]

        val id = intent.getIntExtra(ID, 0)
        val avatarUrl = intent.getStringExtra(AVATAR_URL).toString()
        val username = intent.getStringExtra(USERNAME).toString()

        detailViewModel.userDetail.observe(this, Observer {
            it?.let{
                with(binding) {
                    progBar.visibility = View.GONE
                    tvDetailName.text = it.name
                    tvDetailUsername.text = it.login
                    tvDetailFollowers.text = "${it.followers.toString()} Followers"
                    tvDetailFollowing.text = "${it.following.toString()} Following"
                }
                Glide.with(this)
                    .load(it.avatarUrl)
                    .transform(RoundedCorners(500))
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .into(binding.ivDetailAvatar)
            }
        })
        detailViewModel.setDetailUser(username)

        val viewPagerAdapter = ViewPagerAdapter(this, username)
        val viewPager: ViewPager2 = binding.viewPager
        viewPager.adapter = viewPagerAdapter
        val tabs: TabLayout = binding.tabLayout
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

        var isChecked = false
        CoroutineScope(Dispatchers.IO).launch {
            val count = detailViewModel.checkFavoriteUser(id)
            withContext(Dispatchers.Main) {
                if (count != null) {
                    if (count > 0) {
                        binding.fabFavoriteUser.apply {
                            setImageDrawable(ContextCompat.getDrawable(this@DetailActivity, R.drawable.ic_unfavorite))
                        }
                        isChecked = true
                    } else {
                        binding.fabFavoriteUser.apply {
                            setImageDrawable(ContextCompat.getDrawable(this@DetailActivity, R.drawable.ic_favorite))
                        }
                        isChecked = false
                    }
                }
            }
        }

        binding.fabFavoriteUser.setOnClickListener {
            isChecked = !isChecked
            if (isChecked) {
                detailViewModel.addFavoriteUser(id, username, avatarUrl)
                binding.fabFavoriteUser.apply {
                    setImageDrawable(ContextCompat.getDrawable(this@DetailActivity, R.drawable.ic_unfavorite))
                }
            } else {
                detailViewModel.deleteFavoriteUser(id)
                binding.fabFavoriteUser.apply {
                    setImageDrawable(ContextCompat.getDrawable(this@DetailActivity, R.drawable.ic_favorite))
                }
            }
        }
    }
}