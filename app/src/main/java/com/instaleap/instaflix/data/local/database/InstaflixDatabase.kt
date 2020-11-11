package com.instaleap.instaflix.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.instaleap.instaflix.data.local.database.dao.MoviesDao
import com.instaleap.instaflix.data.local.model.Movie

@Database(entities = [Movie::class], version = 1)
abstract class InstaflixDatabase: RoomDatabase() {
    abstract fun moviesDao(): MoviesDao
}