package com.jabezmagomere.posts.data.remote.models

data class PostResponse(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
)
