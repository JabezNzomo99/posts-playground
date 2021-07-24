package com.jabezmagomere.posts.data.repository

import com.jabezmagomere.posts.data.local.datasource.PostsLocalDataSource
import com.jabezmagomere.posts.data.remote.datasource.PostsRemoteDataSource
import com.jabezmagomere.posts.data.remote.models.NetworkResult
import com.jabezmagomere.posts.data.toPosts
import com.jabezmagomere.posts.ui.models.Result

class PostsRepository(
    private val postsLocalDataSource: PostsLocalDataSource,
    private val remoteDataSource: PostsRemoteDataSource
) {

    fun getPosts() = postsLocalDataSource.getPosts()

    suspend fun fetchPosts(): Result<Boolean> {
        return when (val postsResponse = remoteDataSource.fetchPosts()) {
            is NetworkResult.Success -> {
                if (!postsResponse.data.isNullOrEmpty()) {
                    postsLocalDataSource.insert(postsResponse.data.toPosts())
                    Result.Success(true)
                } else {
                    Result.Success(false)
                }
            }
            is NetworkResult.Error -> {
                Result.Error(postsResponse.exception)
            }
        }
    }
}

