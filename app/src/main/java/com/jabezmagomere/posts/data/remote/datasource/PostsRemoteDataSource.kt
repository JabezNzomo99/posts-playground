package com.jabezmagomere.posts.data.remote.datasource

import com.jabezmagomere.posts.data.remote.models.PostsResponse
import retrofit2.Response

interface PostsRemoteDataSource {
    suspend fun fetchPosts(): Response<PostsResponse>
}

interface PostsRemoteDataSourceImpl