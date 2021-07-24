package com.jabezmagomere.posts.util

import com.jabezmagomere.posts.data.local.models.Post
import com.jabezmagomere.posts.data.remote.models.PostResponse
import com.jabezmagomere.posts.ui.models.PostView

val postViews = (0..5).map { id ->
    PostView(id = id, title = "Test", body = "Test")
}

val posts = (0..5).map { id ->
    Post(id = id, title = "Test", body = "Test", userId = id)
}

val postResponses = (0..5).map { id ->
    PostResponse(id = id, title = "Test", body = "Test", userId = id)
}