package com.jabezmagomere.posts.data

import com.jabezmagomere.posts.data.local.dao.PostsDao
import com.jabezmagomere.posts.data.local.datasource.PostsLocalDataSource
import com.jabezmagomere.posts.data.local.datasource.PostsLocalDataSourceImpl
import com.jabezmagomere.posts.data.local.models.Post
import com.jabezmagomere.posts.util.BaseTest
import com.jabezmagomere.posts.util.postViews
import com.jabezmagomere.posts.util.posts
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
class PostLocalDataSourceTest : BaseTest() {

    private lateinit var postsLocalDataSource: PostsLocalDataSource
    private val postsDao: PostsDao = mockk()

    @Before
    fun setUp() {
        postsLocalDataSource = PostsLocalDataSourceImpl(postsDao)
    }

    @Test
    fun `test whether getPosts() invokes getPosts() in posts dao and returns a flow of post views`() =
        runBlockingTest {
            every {
                postsDao.getPosts()
            } returns flowOf(postViews)

            val posts = postsLocalDataSource.getPosts()

            MatcherAssert.assertThat(posts.first().isNullOrEmpty(), CoreMatchers.`is`(false))

            verify {
                postsDao.getPosts()
            }
        }

    @Test
    fun `test whether insert invokes insert() in posts dao `() = runBlockingTest {
        coEvery {
            postsDao.insert(any<List<Post>>())
        } just Runs

        postsLocalDataSource.insert(posts)

        coVerify {
            postsDao.insert(any<List<Post>>())
        }
    }
}