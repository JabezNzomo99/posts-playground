package com.jabezmagomere.posts.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.jabezmagomere.posts.data.repository.PostsRepository
import com.jabezmagomere.posts.ui.models.PostView
import com.jabezmagomere.posts.ui.models.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MainActivityViewModel(private val repository: PostsRepository) : ViewModel() {

    val posts: Flow<List<PostView>?>
        get() = repository.getPosts()

    fun fetchPosts(): Flow<Result<Boolean>> = flow {
        emit(Result.Loading)
        emit(repository.fetchPosts())
    }

}