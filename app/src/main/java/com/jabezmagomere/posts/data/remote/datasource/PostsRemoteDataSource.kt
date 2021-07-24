package com.jabezmagomere.posts.data.remote.datasource

import android.util.Log
import com.jabezmagomere.posts.data.remote.api.PostsApi
import com.jabezmagomere.posts.data.remote.models.NetworkResult
import com.jabezmagomere.posts.data.remote.models.PostResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface PostsRemoteDataSource {
    suspend fun fetchPosts(): NetworkResult<List<PostResponse>?>
}

class PostsRemoteDataSourceImpl(
    private val postsApi: PostsApi,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : PostsRemoteDataSource {
    override suspend fun fetchPosts(): NetworkResult<List<PostResponse>?> =
        withContext(dispatcher) {
            return@withContext try {
                val response = postsApi.getPosts()
                if (response.isSuccessful && response.body() != null) {
                    NetworkResult.Success(response.body())
                } else {
                    NetworkResult.Error(Exception(response.message()))
                }
            } catch (exception: Exception) {
                Log.e("NETWORK_EXCEPTION", exception.message.toString())
                NetworkResult.Error(exception)
            }
        }

}