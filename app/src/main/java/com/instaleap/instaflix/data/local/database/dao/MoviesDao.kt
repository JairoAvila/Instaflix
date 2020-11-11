package com.instaleap.instaflix.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.instaleap.instaflix.data.local.model.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieList(movieList: List<Movie>)

    @Query("SELECT * FROM Movie WHERE page = :page")
    fun getMovieList(page: Int): Flow<List<Movie>>

}