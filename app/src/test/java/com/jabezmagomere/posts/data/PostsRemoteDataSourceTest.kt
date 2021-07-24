package com.jabezmagomere.posts.data

import com.jabezmagomere.posts.data.remote.api.PostsApi
import com.jabezmagomere.posts.data.remote.datasource.PostsRemoteDataSource
import com.jabezmagomere.posts.data.remote.datasource.PostsRemoteDataSourceImpl
import com.jabezmagomere.posts.data.remote.models.NetworkResult
import com.jabezmagomere.posts.util.BaseTest
import com.jabezmagomere.posts.util.postResponses
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Response
import java.io.IOException

@RunWith(JUnit4::class)
@ExperimentalCoroutinesApi
class PostsRemoteDataSourceTest : BaseTest() {

    private lateinit var postsRemoteDataSource: PostsRemoteDataSource
    private val postsApi: PostsApi = mockk()

    @Before
    fun setUp() {
        postsRemoteDataSource = PostsRemoteDataSourceImpl(postsApi, coroutineTestRule.dispatcher)
    }

    @Test
    fun `test whether fetchPosts() returns network result success if the network request returns 200 `() =
        coroutineTestRule.dispatcher.runBlockingTest {
            coEvery {
                postsApi.getPosts()
            } returns Response.success(200, postResponses)

            val result = postsRemoteDataSource.fetchPosts()

            MatcherAssert.assertThat(
                result,
                CoreMatchers.instanceOf(NetworkResult.Success::class.java)
            )

            coEvery {
                postsApi.getPosts()
            }
        }

    @Test
    fun `test whether fetchPosts() returns network result error if the network request fails with an exception `() =
        runBlockingTest {
            coEvery {
                postsApi.getPosts()
            } throws IOException()

            val result = postsRemoteDataSource.fetchPosts()

            MatcherAssert.assertThat(
                result,
                CoreMatchers.instanceOf(NetworkResult.Error::class.java)
            )

            coEvery {
                postsApi.getPosts()
            }
        }

}