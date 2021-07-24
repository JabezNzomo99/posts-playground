package com.jabezmagomere.posts.data.local.datasource

import com.jabezmagomere.posts.data.local.dao.PostsDao
import com.jabezmagomere.posts.data.local.models.Post
import com.jabezmagomere.posts.ui.models.PostView
import kotlinx.coroutines.flow.Flow

interface PostsLocalDataSource {
    fun getPosts(): Flow<List<PostView>?>
    suspend fun insert(posts: List<Post>)
}

class PostLocalDataSourceImpl(private val postsDao: PostsDao) : PostsLocalDataSource {

    override fun getPosts(): Flow<List<PostView>?> = postsDao.getPosts()

    override suspend fun insert(posts: List<Post>) = postsDao.insert(posts)

}