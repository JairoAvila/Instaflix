package com.instaleap.instaflix.ui.dashboard.fragments.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.instaleap.instaflix.data.local.model.Movie
import com.instaleap.instaflix.data.repository.MoviesRepository
import com.instaleap.instaflix.utils.Resource

class MoviesViewModel(private val repository: MoviesRepository) : ViewModel() {

    val getMovie: LiveData<Resource<List<Movie>>> = repository.getMovieList(1).asLiveData()

}