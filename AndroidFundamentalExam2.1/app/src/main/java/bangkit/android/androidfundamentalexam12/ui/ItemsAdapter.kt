package bangkit.android.androidfundamentalexam12.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import bangkit.android.androidfundamentalexam12.data.response.ItemsItem
import bangkit.android.androidfundamentalexam12.databinding.ItemsRowBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

class ItemsAdapter : ListAdapter<ItemsItem, ItemsAdapter.MyViewHolder>(DIFF_CALLBACK){

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

     inner class MyViewHolder(val binding: ItemsRowBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(items: ItemsItem ){
            binding.root.setOnClickListener {
                onItemClickCallback?.onItemClick(items)
            }
            binding.tvUsername.text = "${items.login}"
            val avatarImageView: ImageView = binding.ivAvatar
            Glide.with(avatarImageView)
                .load(items.avatarUrl)
                .transform(RoundedCorners(100))
                .into(avatarImageView)
        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int){
        val items = getItem(position)
        holder.bind(items)
    }

    companion object{
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ItemsItem>(){
            override fun areItemsTheSame(oldItem: ItemsItem, newItem: ItemsItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: ItemsItem, newItem: ItemsItem): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemsRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    interface OnItemClickCallback{
        fun onItemClick(data: ItemsItem)
    }
}