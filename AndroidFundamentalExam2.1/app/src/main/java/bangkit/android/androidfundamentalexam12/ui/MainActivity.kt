package bangkit.android.androidfundamentalexam12.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import bangkit.android.androidfundamentalexam12.R
import bangkit.android.androidfundamentalexam12.data.response.ItemsItem
import bangkit.android.androidfundamentalexam12.databinding.ActivityMainBinding
import bangkit.android.androidfundamentalexam12.ui.detail.DetailActivity
import bangkit.android.androidfundamentalexam12.ui.favorite.FavoriteUserActivity
import bangkit.android.androidfundamentalexam12.ui.setting.SettingActivity
import bangkit.android.androidfundamentalexam12.ui.setting.SettingPreferences
import bangkit.android.androidfundamentalexam12.ui.setting.SettingViewModel
import bangkit.android.androidfundamentalexam12.ui.setting.SettingViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var adapter: ItemsAdapter

    private val dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ItemsAdapter()
        adapter.setOnItemClickCallback(object : ItemsAdapter.OnItemClickCallback{
            override fun onItemClick(data: ItemsItem) {
                Intent(this@MainActivity, DetailActivity::class.java).also {
                    it.putExtra(DetailActivity.USERNAME, data.login)
                    it.putExtra(DetailActivity.ID, data.id)
                    it.putExtra(DetailActivity.AVATAR_URL, data.avatarUrl)
                    startActivity(it)
                }
            }
        })

        val mainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MainViewModel::class.java)
        mainViewModel.items.observe(this){ItemsItem ->
            setListItems(ItemsItem)
        }
        mainViewModel.isLoading.observe(this){
            showLoading(it)
        }

        val pref = SettingPreferences.getInstance(dataStore)
        val settingViewModel = ViewModelProvider(this, SettingViewModelFactory(pref))[SettingViewModel::class.java]

        settingViewModel.getThemeSettings().observe(this){isDarkModeActive: Boolean ->
            if (isDarkModeActive) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }

        val layoutManager = LinearLayoutManager(this)
        binding.rvListItems.layoutManager = layoutManager

        with(binding){
            sView.setupWithSearchBar(sBar)
            sView
                .editText
                .setOnEditorActionListener{textView, actionId, event ->
                    sBar.text = sView.text
                    val userInput = sBar.text.toString()
                    mainViewModel.setUser(userInput)
                    mainViewModel.getListItems()
                    sView.hide()
                    false
                }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.list_favorite_user -> {
                Intent(this, FavoriteUserActivity::class.java).also {
                    startActivity(it)
                }
            }
            R.id.setting -> {
                Intent(this, SettingActivity::class.java).also {
                    startActivity(it)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setListItems(ResponseItem: List<ItemsItem>){
        adapter.submitList(ResponseItem)
        binding.rvListItems.adapter = adapter
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progBar.visibility = View.VISIBLE
        } else {
            binding.progBar.visibility = View.GONE
        }
    }
}