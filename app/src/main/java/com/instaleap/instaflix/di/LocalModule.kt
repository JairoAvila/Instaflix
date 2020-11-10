package com.instaleap.instaflix.di

import android.app.Application
import androidx.room.Room
import com.instaleap.instaflix.data.local.PreferenceHelper
import com.instaleap.instaflix.data.local.database.InstaflixDatabase
import com.instaleap.instaflix.data.local.database.dao.MoviesDao
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

const val DATABASE_NAME = "instaflix_db"

val localModule = module {
    single { providePreferenceHelper(androidApplication()) }
    single { provideRoom(androidApplication()) }
    single { provideMovieDao(get()) }
}

fun providePreferenceHelper(androidApplication: Application): PreferenceHelper =
    PreferenceHelper(androidApplication)

fun provideRoom(application: Application): InstaflixDatabase {
    return Room.databaseBuilder(
        application.applicationContext,
        InstaflixDatabase::class.java,
        DATABASE_NAME
    ).build()
}

fun provideMovieDao(instaflixDatabase: InstaflixDatabase): MoviesDao = instaflixDatabase.moviesDao()