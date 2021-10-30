package com.safeer.entertainia.ui.main.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
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
        /*itemView.imageView.load(post.imageUrl) {
            placeholder(R.drawable.ic_photo)
            error(R.drawable.ic_broken_image)
        }*/

        itemView.setOnClickListener {
            onItemClicked(post, imageView)
        }
    }
}
