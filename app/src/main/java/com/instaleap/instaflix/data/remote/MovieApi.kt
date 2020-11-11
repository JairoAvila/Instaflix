package com.instaleap.instaflix.data.remote

import com.instaleap.instaflix.data.remote.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/popular")
    suspend fun fetchMovieList(
        @Query("page") page: Int,
        @Query("language") language: String = "es"
    ): MovieResponse

}