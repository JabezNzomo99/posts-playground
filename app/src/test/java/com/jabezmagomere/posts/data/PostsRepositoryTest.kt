package com.jabezmagomere.posts.data

import com.jabezmagomere.posts.data.local.datasource.PostsLocalDataSource
import com.jabezmagomere.posts.data.remote.datasource.PostsRemoteDataSource
import com.jabezmagomere.posts.data.remote.models.NetworkResult
import com.jabezmagomere.posts.data.repository.PostsRepository
import com.jabezmagomere.posts.ui.models.Result
import com.jabezmagomere.posts.util.BaseTest
import com.jabezmagomere.posts.util.postResponses
import com.jabezmagomere.posts.util.postViews
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class PostsRepositoryTest : BaseTest() {

    private lateinit var repository: PostsRepository
    private val postsLocalDataSource: PostsLocalDataSource = mockk()
    private val postsRemoteDataSource: PostsRemoteDataSource = mockk()


    @Before
    fun setUp() {
        repository = PostsRepository(postsLocalDataSource, postsRemoteDataSource)
    }

    @Test
    fun `test whether getPosts() invokes getPosts() in the local data source and returns a flow of post views`() =
        runBlockingTest {
            every {
                postsLocalDataSource.getPosts()
            } returns flowOf(postViews)

            val posts = repository.getPosts()

            MatcherAssert.assertThat(posts.first().isNullOrEmpty(), CoreMatchers.`is`(false))

            verify {
                postsLocalDataSource.getPosts()
            }
        }

    @Test
    fun `test whether fetchPosts() invokes fetchPosts() in the remote data source and returns the right result`() =
        runBlockingTest {
            coEvery {
                postsRemoteDataSource.fetchPosts()
            } returns NetworkResult.Success(postResponses)

            coEvery {
                postsLocalDataSource.insert(any())
            } just Runs

            val result = repository.fetchPosts()

            MatcherAssert.assertThat(result, CoreMatchers.instanceOf(Result.Success::class.java))

            coEvery {
                postsRemoteDataSource.fetchPosts()
                postsLocalDataSource.insert(any())
            }
        }

}