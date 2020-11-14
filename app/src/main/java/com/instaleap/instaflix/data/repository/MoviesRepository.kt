package com.instaleap.instaflix.data.repository

import com.instaleap.instaflix.data.datasource.LocalDataSource
import com.instaleap.instaflix.data.datasource.RemoteDataSource
import com.instaleap.instaflix.data.local.model.Movie
import com.instaleap.instaflix.data.remote.model.MovieResponse
import com.instaleap.instaflix.utils.NetworkBoundResource
import com.instaleap.instaflix.utils.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

@ExperimentalCoroutinesApi
class MoviesRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) {

    fun getMovieList(page: Int): Flow<Resource<List<Movie>>> {
        return object : NetworkBoundResource<List<Movie>, MovieResponse>() {

            override fun processResponse(response: MovieResponse): List<Movie> {
                val movie: MutableList<Movie> = mutableListOf()
                response.results.forEach { responseResult ->
                    movie.add(
                        Movie(
                            id = responseResult.id,
                            page = response.page,
                            title = responseResult.title,
                            poster = responseResult.poster_path,
                            genre = responseResult.genre_ids
                        )
                    )
                }
                return movie.toList()
            }

            override suspend fun saveNetworkResult(items: List<Movie>) {
                return localDataSource.insertMovieList(items)
            }

            override fun shouldFetch(data: List<Movie>?): Boolean {
                return true
            }

            override fun loadFromDb(): Flow<List<Movie>> {
                return localDataSource.getMovieList(page)
            }

            override suspend fun fetchFromNetwork(): Resource<MovieResponse> {
                return remoteDataSource.getMovieListRemote(page)
            }

        }.asFlow()
    }

}