package com.instaleap.instaflix

import android.app.Application
import com.instaleap.instaflix.di.localModule
import com.instaleap.instaflix.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class InstaflixApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() = startKoin {
        androidContext(this@InstaflixApplication)
        modules(arrayListOf(localModule, networkModule))
    }

}