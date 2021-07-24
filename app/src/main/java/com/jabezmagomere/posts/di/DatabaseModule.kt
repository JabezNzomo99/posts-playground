package com.jabezmagomere.posts.di

import android.content.Context
import androidx.room.Room
import com.jabezmagomere.posts.data.local.Database
import com.jabezmagomere.posts.data.local.dao.PostsDao
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    fun provideDatabase(context: Context): Database =
        Room.databaseBuilder(context, Database::class.java, "posts.db").build()

    fun providePostsDao(database: Database): PostsDao = database.postsDao()

    single { provideDatabase(androidContext()) }
    single { providePostsDao(get()) }
}
