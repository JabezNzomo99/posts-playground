package com.jabezmagomere.posts.di

import com.google.gson.Gson
import com.jabezmagomere.posts.data.remote.api.PostsApi
import com.jabezmagomere.posts.data.remote.datasource.PostsRemoteDataSource
import com.jabezmagomere.posts.data.remote.datasource.PostsRemoteDataSourceImpl
import com.jabezmagomere.posts.util.BASE_URL
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val remoteDataSourceModule = module {
    fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    fun providePostsApi(retrofit: Retrofit): PostsApi = retrofit.create(PostsApi::class.java)

    fun providePostsRemoteDataSource(postsApi: PostsApi): PostsRemoteDataSource =
        PostsRemoteDataSourceImpl(postsApi)

    single { provideRetrofit(get(), get()) }
    single { providePostsApi(get()) }
    single { providePostsRemoteDataSource(get()) }
}
