package com.safeer.entertainia.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.safeer.entertainia.R
import com.safeer.entertainia.model.Post

import com.safeer.entertainia.ui.main.viewholder.PostViewHolder


class PostListAdapter(
    private val onItemClicked: (Post, ImageView) -> Unit
) : ListAdapter<Post, PostViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PostViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_post,parent, false)
        )

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) =
        holder.bind(getItem(position), onItemClicked)

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Post>() {
            override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean =
                oldItem == newItem
        }
    }
}
