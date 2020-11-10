package com.instaleap.instaflix.di

import com.instaleap.instaflix.ui.dashboard.fragments.movies.MoviesViewModel
import com.instaleap.instaflix.ui.dashboard.fragments.tvseries.TvSeriesViewModel
import com.instaleap.instaflix.ui.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewmodelModule = module {
    viewModel { SplashViewModel() }
    viewModel { MoviesViewModel(get()) }
    viewModel { TvSeriesViewModel(get()) }
}