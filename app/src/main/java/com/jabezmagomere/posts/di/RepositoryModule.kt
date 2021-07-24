package com.jabezmagomere.posts.di

import com.jabezmagomere.posts.data.local.datasource.PostsLocalDataSource
import com.jabezmagomere.posts.data.remote.datasource.PostsRemoteDataSource
import com.jabezmagomere.posts.data.repository.PostsRepository
import org.koin.dsl.module

val repositoryModule = module {

    fun providePostsRepository(
        postsLocalDataSource: PostsLocalDataSource,
        postsRemoteDataSource: PostsRemoteDataSource
    ): PostsRepository = PostsRepository(postsLocalDataSource, postsRemoteDataSource)

    single {
        providePostsRepository(get(), get())
    }
}
