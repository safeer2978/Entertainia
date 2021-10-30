package com.safeer.entertainia.ui.main

import android.os.Bundle
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.safeer.entertainia.R
import com.safeer.entertainia.model.Post
import com.safeer.entertainia.model.State
import com.safeer.entertainia.ui.main.viewholder.NetworkUtils
import dagger.hilt.android.AndroidEntryPoint
import com.safeer.entertainia.ui.main.adapter.PostListAdapter
import kotlinx.coroutines.flow.collect


@AndroidEntryPoint
class MainActivity2 : AppCompatActivity() {
    val viewModel: MainViewModel by viewModels()

    val adapter =  PostListAdapter(::onItemClicked)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        observePosts()
        handleNetworkChanges()
        val rv = findViewById<RecyclerView>(R.id.rv_main)
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(this)
    }


    private fun handleNetworkChanges() {
        NetworkUtils.getNetworkLiveData(applicationContext).observe(this) { isConnected ->
            if (!isConnected) {

            } else {
                if (adapter.itemCount == 0) viewModel.getPosts()
                //mViewBinding.textViewNetworkStatus.text = getString(R.string.text_connectivity)
                //setBackgroundColor(getColorRes(R.color.colorStatusConnected))
            }
        }
    }


    private fun onItemClicked(post: Post, imageView: ImageView) {
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
            this,
            imageView,
            imageView.transitionName
        )
        val postId = post.id ?: run {
            Snackbar.make(findViewById(R.id.rv_main), "Unable to launch details", Snackbar.LENGTH_LONG).show()
            return
        }
        //val intent = PostDetailsActivity.getStartIntent(this, postId)
        //startActivity(intent, options.toBundle())
    }

    private fun observePosts() {
        lifecycleScope.launchWhenStarted {
            viewModel.posts.collect { state ->
                when (state) {
                    is State.Loading -> Unit //showLoading(true)
                    is State.Success -> {
                        if (state.data.isNotEmpty()) {
                            adapter.submitList(state.data.toMutableList())
                           // showLoading(false)
                        }
                    }
                    is State.Error -> {
                        //showToast(state.message)
                        //showLoading(false)
                    }
                }
            }
        }
    }



}