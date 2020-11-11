package com.instaleap.instaflix.data.datasource

import com.instaleap.instaflix.data.remote.MovieApi
import com.instaleap.instaflix.data.remote.model.MovieResponse
import com.instaleap.instaflix.utils.Resource
import com.instaleap.instaflix.utils.ResponseHandler
import java.lang.Exception

class RemoteDataSource(
    private val movieApi: MovieApi,
    private val responseHandler: ResponseHandler
) {

    suspend fun getMovieListRemote(page: Int): Resource<MovieResponse> =
        try {
            val response = movieApi.fetchMovieList(page)
            responseHandler.handleSuccess(response)
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }


}