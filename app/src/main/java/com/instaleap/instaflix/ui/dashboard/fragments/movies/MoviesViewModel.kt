package com.instaleap.instaflix.ui.dashboard.fragments.movies

import androidx.lifecycle.*
import com.instaleap.instaflix.data.local.model.Movie
import com.instaleap.instaflix.data.repository.MoviesRepository
import com.instaleap.instaflix.utils.Resource


class MoviesViewModel(private val repository: MoviesRepository) : ViewModel() {

    private var pageNumber = MutableLiveData<Int>()

    val movies: LiveData<Resource<List<Movie>>> = Transformations.switchMap(pageNumber) { page -> repository.getMovieList(page).asLiveData(viewModelScope.coroutineContext)}

    fun getMovies(page: Int) = pageNumber.postValue(page)

}