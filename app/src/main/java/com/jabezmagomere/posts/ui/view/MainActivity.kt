package com.jabezmagomere.posts.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.jabezmagomere.posts.databinding.ActivityMainBinding
import com.jabezmagomere.posts.ui.adapters.PostsAdapter
import com.jabezmagomere.posts.ui.models.PostView
import com.jabezmagomere.posts.ui.models.Result
import com.jabezmagomere.posts.ui.viewmodel.MainActivityViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainActivityViewModel: MainActivityViewModel by viewModel()
    private lateinit var postsAdapter: PostsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpPostsRv()
        getPosts()
    }

    private fun setUpPostsRv() {
        postsAdapter = PostsAdapter()
        binding.rvPosts.adapter = postsAdapter
    }

    private fun setUpSwipeRefresh() {

    }

    private fun getPosts() {
        mainActivityViewModel.posts.onEach { posts ->
            if (!posts.isNullOrEmpty()) {
                showPosts(posts)
            } else {
                showEmptyState()
                fetchPosts()
            }

        }.launchIn(lifecycleScope)
    }

    private fun showPosts(posts: List<PostView>) {
        postsAdapter.submitList(posts)
    }

    private fun showEmptyState() {

    }

    private fun fetchPosts() {
        mainActivityViewModel.fetchPosts().onEach { fetchPostsState ->
            when (fetchPostsState) {
                is Result.Error -> {
                    showErrorState(fetchPostsState.exception)
                }
                Result.Loading -> {
                    showLoadingState()
                }
                is Result.Success -> {
                    showSuccessState()
                }
            }
        }.launchIn(lifecycleScope)
    }

    private fun showErrorState(exception: Exception) {

    }

    private fun showLoadingState() {

    }

    private fun showSuccessState() {

    }

}