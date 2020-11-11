package com.instaleap.instaflix.data.datasource

import com.instaleap.instaflix.data.local.database.dao.MoviesDao
import com.instaleap.instaflix.data.local.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class LocalDataSource(
    private val moviesDao: MoviesDao
) {

    suspend fun insertMovieList(movieList: List<Movie>) =
        withContext(Dispatchers.IO) {
            moviesDao.insertMovieList(movieList)
        }

    fun getMovieList(page: Int): Flow<List<Movie>> = moviesDao.getMovieList(page)
}