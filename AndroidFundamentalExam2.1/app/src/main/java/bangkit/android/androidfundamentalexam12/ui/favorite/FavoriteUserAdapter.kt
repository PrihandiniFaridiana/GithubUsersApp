package bangkit.android.androidfundamentalexam12.ui.favorite

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import bangkit.android.androidfundamentalexam12.R
import bangkit.android.androidfundamentalexam12.data.local.FavoriteUser
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

class FavoriteUserAdapter: RecyclerView.Adapter<FavoriteUserAdapter.MyViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback
    private val listFavoriteUser = ArrayList<FavoriteUser>()

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var ivAvatar: ImageView = itemView.findViewById(R.id.iv_avatar)
        var tvUsername: TextView = itemView.findViewById(R.id.tv_username)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.items_row, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val listUser = listFavoriteUser[position]
        Glide.with(holder.itemView.context)
            .load(listUser.avatar_url)
            .transform(RoundedCorners(100))
            .into(holder.ivAvatar)
        holder.tvUsername.text = listUser.username

        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClick(listFavoriteUser[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int {
        return listFavoriteUser.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setListFavoriteUser(list: ArrayList<FavoriteUser>) {
        listFavoriteUser.clear()
        listFavoriteUser.addAll(list)
        notifyDataSetChanged()
    }

    interface OnItemClickCallback {
        fun onItemClick(data: FavoriteUser)
    }

}