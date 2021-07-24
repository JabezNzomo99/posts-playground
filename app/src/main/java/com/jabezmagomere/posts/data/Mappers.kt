package com.jabezmagomere.posts.data

import com.jabezmagomere.posts.data.local.models.Post
import com.jabezmagomere.posts.data.remote.models.PostResponse

fun List<PostResponse>.toPosts(): List<Post> {
    return this.map { postResponse ->
        Post(
            userId = postResponse.userId,
            id = postResponse.id,
            title = postResponse.title,
            body = postResponse.body
        )
    }
}