package com.gibox.testandroid.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gibox.testandroid.R
import com.gibox.testandroid.core.data.auth.source.remote.response.DataItem
import com.gibox.testandroid.databinding.ItemUserBinding

class UserAdapter : PagingDataAdapter<DataItem, UserAdapter.ViewHolder>(DATA_ITEM_COMPARATOR) {

    inner class ViewHolder(val itemUserBinding: ItemUserBinding): RecyclerView.ViewHolder(itemUserBinding.root)

    override fun onBindViewHolder(holder: UserAdapter.ViewHolder, position: Int) {
        val item = getItem(position)
        with(holder.itemUserBinding) {
            txtUsername.text = item?.getFullName()
            txtEmail.text = item?.email
            item?.avatar?.let {
                Glide.with(this.root.context)
                    .load(it).placeholder(R.drawable.ic_default_profile)
                    .into(imgProfilePicture)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.ViewHolder {
        return ViewHolder(ItemUserBinding.inflate(LayoutInflater.from(parent.context)))
    }

}

val DATA_ITEM_COMPARATOR = object : DiffUtil.ItemCallback<DataItem>() {
    override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean =
        // User ID serves as unique ID
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean =
        // Compare full contents (note: Java users should call .equals())
        oldItem == newItem
}