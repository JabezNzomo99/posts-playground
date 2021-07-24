package com.jabezmagomere.posts.viewmodels

import com.jabezmagomere.posts.data.repository.PostsRepository
import com.jabezmagomere.posts.ui.models.Result
import com.jabezmagomere.posts.ui.viewmodel.MainActivityViewModel
import com.jabezmagomere.posts.util.BaseTest
import com.jabezmagomere.posts.util.postViews
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class MainActivityViewModelTest : BaseTest() {

    private lateinit var mainActivityViewModel: MainActivityViewModel
    private val postsRepository: PostsRepository = mockk()

    @Before
    fun setUp() {
        mainActivityViewModel = MainActivityViewModel(postsRepository)
    }

    @Test
    fun `test whether posts invokes getPosts() in the posts repository and returns a flow of post views`() =
        runBlockingTest {
            every {
                postsRepository.getPosts()
            } returns flowOf(postViews)

            val posts = mainActivityViewModel.posts

            assertThat(posts.first().isNullOrEmpty(), `is`(false))

            verify {
                postsRepository.getPosts()
            }
        }

    @Test
    fun `test whether fetchPosts() invokes getPosts() in the posts repository and returns a flow with ui state`() =
        runBlockingTest {
            coEvery {
                postsRepository.fetchPosts()
            } returns Result.Success(true)

            val response = mainActivityViewModel.fetchPosts()

            assertThat(response.first(), instanceOf(Result.Loading::class.java))
        }


}