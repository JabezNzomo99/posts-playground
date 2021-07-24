package com.jabezmagomere.posts.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.jabezmagomere.posts.data.local.models.Post
import com.jabezmagomere.posts.ui.models.PostView
import kotlinx.coroutines.flow.Flow

@Dao
interface PostsDao : BaseDao<Post> {

    @Query("Select id, title, body from posts ORDER BY id ASC")
    fun getPosts(): Flow<List<PostView>?>
}