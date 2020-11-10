package com.instaleap.instaflix.di

import com.instaleap.instaflix.ui.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewmodelModule = module {
    viewModel { SplashViewModel() }
}