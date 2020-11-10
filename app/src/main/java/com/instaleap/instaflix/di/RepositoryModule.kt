package com.instaleap.instaflix.di

import com.instaleap.instaflix.data.repository.MoviesRepository
import com.instaleap.instaflix.data.repository.TvSeriesRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { MoviesRepository(get(), get()) }
    single { TvSeriesRepository(get(), get()) }
}