package com.instaleap.instaflix.data.datasource

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.instaleap.instaflix.data.remote.MovieApi
import com.instaleap.instaflix.data.remote.model.MovieResponse
import com.instaleap.instaflix.utils.MainCoroutineRule
import com.instaleap.instaflix.utils.ResponseHandler
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import retrofit2.HttpException

@ExperimentalCoroutinesApi
class RemoteDataSourceTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    val coroutinesRule = MainCoroutineRule()

    @Mock
    private val fakeMovieApi: MovieApi = Mockito.mock(MovieApi::class.java)

    @Mock
    private val fakeResponseHandler: ResponseHandler = Mockito.mock(ResponseHandler::class.java)

    private lateinit var fakeDataSource: RemoteDataSource

    @Before
    fun onSetup() {
        fakeDataSource = RemoteDataSource(fakeMovieApi, fakeResponseHandler)
    }

    @Test
    fun `should return a successful response from the movie service`() {
        runBlocking {
            val obj = MovieResponse(1,1,1, listOf())

            Mockito.`when`(fakeMovieApi.fetchMovieList(1)).thenReturn(obj)

            assertEquals(fakeResponseHandler.handleSuccess(obj), fakeDataSource.getMovieListRemote(1))
        }
    }

    @Test
    fun `should return a failed response from the movie service`() {
        runBlocking {
            val mockException: HttpException = Mockito.mock(HttpException::class.java)
            Mockito.`when`(mockException.code()).thenReturn(401)

            Mockito.`when`(fakeMovieApi.fetchMovieList(1)).thenThrow(mockException)

            assertEquals(fakeResponseHandler.handleException<HttpException>(mockException), fakeDataSource.getMovieListRemote(1))
        }
    }

}