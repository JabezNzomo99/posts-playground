package com.jabezmagomere.posts.di

import com.jabezmagomere.posts.data.local.dao.PostsDao
import com.jabezmagomere.posts.data.local.datasource.PostsLocalDataSource
import com.jabezmagomere.posts.data.local.datasource.PostsLocalDataSourceImpl
import org.koin.dsl.module

val localDataSourceModules = module {
    fun providePostsLocalDataSource(postsDao: PostsDao): PostsLocalDataSource =
        PostsLocalDataSourceImpl(postsDao)

    single {
        providePostsLocalDataSource(get())
    }
}
