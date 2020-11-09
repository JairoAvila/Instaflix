package com.instaleap.instaflix.di

import android.app.Application
import com.instaleap.instaflix.data.local.PreferenceHelper
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val localModule = module {
    single { providePreferenceHelper(androidApplication()) }
}

fun providePreferenceHelper(androidApplication: Application): PreferenceHelper =
    PreferenceHelper(androidApplication)