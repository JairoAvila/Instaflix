package com.instaleap.instaflix.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.instaleap.instaflix.ui.dashboard.DashboardActivity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onStart

@ExperimentalCoroutinesApi
class SplashViewModel: ViewModel() {

    val launchActivityEvent = flowOf(DashboardActivity::class.simpleName)
        .onStart { delay(SPLASH_DURATION) }
        .asLiveData()

    companion object {
        const val SPLASH_DURATION = 5_000L
    }

}