package com.jabezmagomere.posts.data.remote.api

import com.jabezmagomere.posts.data.remote.models.PostResponse
import retrofit2.Response
import retrofit2.http.GET

interface PostsApi {

    @GET("posts")
    fun getPosts(): Response<List<PostResponse>>
}