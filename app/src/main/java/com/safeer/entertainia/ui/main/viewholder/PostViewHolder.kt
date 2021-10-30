package com.safeer.entertainia.ui.main.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.safeer.entertainia.R
import com.safeer.entertainia.model.Post
import com.safeer.entertainia.model.State.Companion.error



class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val postTitle = itemView.findViewById<TextView>(R.id.post_title)
    val postAuthor = itemView.findViewById<TextView>(R.id.post_author)
    val imageView = itemView.findViewById<ImageView>(R.id.imageView)

    fun bind(post: Post, onItemClicked: (Post, ImageView) -> Unit) {
        postTitle.text = post.title
        postAuthor.text = post.author
        val requestOptions = RequestOptions()
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
        Glide.with(itemView.context)
            .applyDefaultRequestOptions(requestOptions)
            .load(post.imageUrl)
            .into(imageView)

        itemView.setOnClickListener {
            onItemClicked(post, imageView)
        }
    }
}
