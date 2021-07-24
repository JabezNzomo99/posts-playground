package com.jabezmagomere.posts.data.repository

import com.jabezmagomere.posts.data.local.datasource.PostsLocalDataSource
import com.jabezmagomere.posts.data.remote.datasource.PostsRemoteDataSource
import com.jabezmagomere.posts.data.remote.models.NetworkResult
import com.jabezmagomere.posts.data.toPosts
import com.jabezmagomere.posts.ui.models.Result
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class PostsRepository(
    private val postsLocalDataSource: PostsLocalDataSource,
    private val remoteDataSource: PostsRemoteDataSource
) {
    suspend fun getPosts() = flow {
        postsLocalDataSource.getPosts().map { posts ->
            if (posts.isNullOrEmpty()) {
                when (val postsResponse = remoteDataSource.fetchPosts()) {
                    is NetworkResult.Success -> {
                        if (!postsResponse.data.isNullOrEmpty()) {
                            postsLocalDataSource.insert(postsResponse.data.toPosts())
                        }
                    }
                    is NetworkResult.Error -> {
                        emit(Result.Error(postsResponse.exception))
                    }
                }
            } else {
                emit(Result.Success(posts))
            }
        }
    }
}

