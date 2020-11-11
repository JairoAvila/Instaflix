package com.instaleap.instaflix.di

import com.instaleap.instaflix.data.datasource.LocalDataSource
import com.instaleap.instaflix.data.datasource.RemoteDataSource
import org.koin.dsl.module

val dataSourceModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get(), get()) }
}