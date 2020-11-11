package com.instaleap.instaflix.ui.dashboard.fragments.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.instaleap.instaflix.data.local.model.Movie
import com.instaleap.instaflix.data.repository.MoviesRepository
import com.instaleap.instaflix.utils.MainCoroutineRule
import com.instaleap.instaflix.utils.Resource
import com.instaleap.instaflix.utils.Status
import com.instaleap.instaflix.utils.getOrAwaitValue
import com.winterbe.expekt.should
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito

@ExperimentalCoroutinesApi
class MoviesViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    val coroutinesRule = MainCoroutineRule()

    @Mock
    val fakeRepo: MoviesRepository = Mockito.mock(MoviesRepository::class.java)

    @Test
    fun `Check local data flow`() {

        val fakeMovie = Mockito.mock(Movie::class.java).apply {
            id = 1
            page = 1
            title = "fake movie 1"
            genre = 1
            poster = "fakemovie1.png"

        }

        val fakeMovie2 = Mockito.mock(Movie::class.java).apply {
            id = 2
            page = 2
            title = "fake movie 2"
            genre = 2
            poster = "fakemovie2.png"

        }

        Mockito.`when`(fakeRepo.getMovieList(1)).thenReturn(flow {
            emit(Resource.loading())
            emit(Resource.success(listOf(fakeMovie, fakeMovie2)))
        })

        val viewModel = MoviesViewModel(fakeRepo)

        val firstData = viewModel.getMovie.getOrAwaitValue()
        firstData.status.should.equal(Status.LOADING)

        val secondData = viewModel.getMovie.getOrAwaitValue()
        secondData.status.should.equal(Status.SUCCESS)

    }

}