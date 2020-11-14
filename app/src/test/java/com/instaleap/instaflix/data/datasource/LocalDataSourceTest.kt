package com.instaleap.instaflix.data.datasource

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.instaleap.instaflix.data.local.database.dao.MoviesDao
import com.instaleap.instaflix.data.local.model.Movie
import com.instaleap.instaflix.utils.MainCoroutineRule
import com.winterbe.expekt.should
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito

@ExperimentalCoroutinesApi
class LocalDataSourceTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    val coroutinesRule = MainCoroutineRule()

    @Mock
    private val fakeMovieDao: MoviesDao = Mockito.mock(MoviesDao::class.java)

    private lateinit var fakeDataSource: LocalDataSource

    @Before
    fun onSetup() {
        fakeDataSource = LocalDataSource(fakeMovieDao)
    }

    @Test
    fun `should return the movie list from the movie table`() {
        runBlocking {
            val fakeMovie = Mockito.mock(Movie::class.java).apply {
                id = 1
                page = 1
                title = "fake movie 1"
                genre = listOf(1,2,3)
                poster = "fakemovie1.png"

            }

            val fakeMovie2 = Mockito.mock(Movie::class.java).apply {
                id = 2
                page = 2
                title = "fake movie 2"
                genre = listOf(1,2,3)
                poster = "fakemovie2.png"

            }

            Mockito.`when`(fakeMovieDao.getMovieList(1)).thenReturn(flow{ emit(listOf(fakeMovie, fakeMovie2)) } )

            val firstData = fakeDataSource.getMovieList(1)
            firstData.first()[0].should.equal(fakeMovie)

        }
    }


}