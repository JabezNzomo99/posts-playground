package com.jabezmagomere.posts.di

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import java.util.concurrent.TimeUnit

val networkModule = module {
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    fun provideGson(): Gson = Gson()
        .newBuilder()
        .create()

    fun provideOkhttpInterceptor(
        httpLoggingInterceptor: HttpLoggingInterceptor,
    ) = OkHttpClient()
        .newBuilder()
        .addInterceptor(httpLoggingInterceptor)
        .connectTimeout(20, TimeUnit.SECONDS)
        .readTimeout(20, TimeUnit.SECONDS)
        .build()

    single { provideHttpLoggingInterceptor() }
    single { provideGson() }
    single { provideOkhttpInterceptor(get()) }
}