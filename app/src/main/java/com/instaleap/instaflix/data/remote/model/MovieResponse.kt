package com.instaleap.instaflix.data.remote.model


data class MovieResponse(
    val page: Int,
    val total_results: Int,
    val total_pages: Int,
    val results: List<Movie>
) {
    data class Movie (
        val id: Int,
        val title: String,
        val genre_ids: List<Int>,
        val poster_path: String
    )
}