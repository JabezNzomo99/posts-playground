package com.jabezmagomere.posts.ui.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.jabezmagomere.posts.R
import com.jabezmagomere.posts.databinding.ActivityMainBinding
import com.jabezmagomere.posts.ui.adapters.PostsAdapter
import com.jabezmagomere.posts.ui.models.PostView
import com.jabezmagomere.posts.ui.models.Result
import com.jabezmagomere.posts.ui.viewmodel.MainActivityViewModel
import com.jabezmagomere.posts.util.hide
import com.jabezmagomere.posts.util.show
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
        binding.rvPosts.show()
        postsAdapter.submitList(posts)
    }

    private fun showEmptyState() {
        binding.uiState.show()
        binding.loadingState.hide()
        binding.rvPosts.hide()
        binding.imageViewUIState.setImageDrawable(
            ContextCompat.getDrawable(
                this,
                R.drawable.ic_empty
            )
        )
        binding.tvUIState.text = getString(R.string.no_posts)
    }

    private fun fetchPosts() {
        mainActivityViewModel.fetchPosts().onEach { fetchPostsState ->
            when (fetchPostsState) {
                is Result.Error -> {
                    showErrorState()
                }
                Result.Loading -> {
                    showLoadingState()
                }
                is Result.Success -> {
                    showSuccessState(fetchPostsState.data)
                }
            }
        }.launchIn(lifecycleScope)
    }

    private fun showErrorState() {
        binding.rvPosts.hide()
        binding.loadingState.hide()
        binding.imageViewUIState.setImageDrawable(
            ContextCompat.getDrawable(
                this,
                R.drawable.ic_error
            )
        )
        binding.tvUIState.text = getString(R.string.error_occured)
    }

    private fun showLoadingState() {
        binding.rvPosts.hide()
        binding.loadingState.show()
        binding.uiState.hide()
    }

    private fun showSuccessState(isSuccess: Boolean) {
        if (isSuccess) {
            binding.loadingState.hide()
            binding.uiState.hide()
        } else {
            binding.loadingState.hide()
            binding.uiState.hide()
            showEmptyState()
        }
    }

    @Suppress("UNUSED")
    private fun refresh(view: View) {
        fetchPosts()
    }
}