package com.jabezmagomere.posts.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jabezmagomere.posts.data.local.dao.PostsDao
import com.jabezmagomere.posts.data.local.models.Post

@Database(
    entities = [Post::class],
    version = 1
)
abstract class Database : RoomDatabase() {
    abstract fun postsDao(): PostsDao
}