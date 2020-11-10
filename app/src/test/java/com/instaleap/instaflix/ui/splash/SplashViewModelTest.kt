package com.instaleap.instaflix.ui.splash

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.instaleap.instaflix.ui.dashboard.DashboardActivity
import com.instaleap.instaflix.utils.MainCoroutineRule
import com.instaleap.instaflix.utils.getOrAwaitValue
import com.winterbe.expekt.should
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class SplashViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    val coroutinesRule = MainCoroutineRule()

    private lateinit var splashViewModel: SplashViewModel

    @Before
    fun init() {
        splashViewModel = SplashViewModel()
    }

    @Test
    fun `After splash, should go to feed`() {
        val actualResult = splashViewModel.launchActivityEvent.getOrAwaitValue {
            coroutinesRule.advanceTimeBy(SplashViewModel.SPLASH_DURATION)
        }
        actualResult.should.equal(DashboardActivity::class.simpleName)
    }

}