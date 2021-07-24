package com.jabezmagomere.posts.util

import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule

open class BaseTest {
    /**
     * Switches the coroutine dispatcher when testing to a test dispatcher
     */
    @ExperimentalCoroutinesApi
    @get:Rule
    open val coroutineTestRule: CoroutineTestRule = CoroutineTestRule()
}