package com.instaleap.instaflix.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.instaleap.instaflix.databinding.ActivitySplashBinding
import com.instaleap.instaflix.ui.dashboard.DashboardActivity
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class SplashActivity : AppCompatActivity() {

    private val viewmodel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewmodel.launchActivityEvent.observe(this, Observer { activityName ->
            when(activityName){
                DashboardActivity::class.simpleName -> startActivity(Intent(this, DashboardActivity::class.java))
                else -> throw IllegalArgumentException("Undefined activity id $activityName")
            }
            finish()
        })

    }

}