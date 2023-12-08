package bangkit.android.androidfundamentalexam12.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import bangkit.android.androidfundamentalexam12.data.response.Follower
import bangkit.android.androidfundamentalexam12.data.response.Following
import bangkit.android.androidfundamentalexam12.databinding.ItemsRowBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

class FollowerAdapter: ListAdapter<Follower, FollowerAdapter.MyViewHolder>(DIFF_CALLBACK) {

    class MyViewHolder(val binding: ItemsRowBinding): RecyclerView.ViewHolder(binding.root){
        fun bind (follower: Follower){
            binding.tvUsername.text = "${follower.login}"
            val avatarImageView: ImageView = binding.ivAvatar
            Glide.with(avatarImageView)
                .load(follower.avatarUrl)
                .transform(RoundedCorners(100))
                .into(avatarImageView)
        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int){
        val items = getItem(position)
        holder.bind(items)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemsRowBinding.inflate(LayoutInflater.from(parent.context))
        return MyViewHolder(binding)
    }

    companion object{
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Follower>(){
            override fun areItemsTheSame(oldItem: Follower, newItem: Follower): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: Follower, newItem: Follower): Boolean {
                return oldItem == newItem
            }
        }
    }
}

class FollowingAdapter: ListAdapter<Following, FollowingAdapter.MyViewHolder>(DIFF_CALLBACK) {

    class MyViewHolder(val binding: ItemsRowBinding): RecyclerView.ViewHolder(binding.root){
        fun bind (following: Following){
            binding.tvUsername.text = "${following.login}"
           val avatarImageView: ImageView = binding.ivAvatar
            Glide.with(avatarImageView)
               .load(following.avatarUrl)
              .into(avatarImageView)
        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int){
        val items = getItem(position)
        holder.bind(items)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemsRowBinding.inflate(LayoutInflater.from(parent.context))
        return MyViewHolder(binding)
    }

    companion object{
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Following>(){
            override fun areItemsTheSame(oldItem: Following, newItem: Following): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: Following, newItem: Following): Boolean {
                return oldItem == newItem
            }
        }
    }
}