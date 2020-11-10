package com.instaleap.instaflix.data.local.database

import androidx.room.RoomDatabase
import com.instaleap.instaflix.data.local.database.dao.MoviesDao

abstract class InstaflixDatabase: RoomDatabase() {
    abstract fun moviesDao(): MoviesDao
}