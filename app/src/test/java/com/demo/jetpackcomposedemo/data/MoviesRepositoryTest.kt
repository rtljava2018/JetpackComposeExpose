package com.demo.jetpackcomposedemo.data

import com.demo.jetpackcomposedemo.domain.ErrorType
import com.demo.jetpackcomposedemo.domain.MovieRepository
import com.demo.jetpackcomposedemo.fakeSuccessMappedResponse
import com.demo.jetpackcomposedemo.fakeSuccessMovieResponse
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test
import retrofit2.Response
import com.demo.jetpackcomposedemo.domain.Result
import okhttp3.ResponseBody.Companion.toResponseBody

class MoviesRepositoryTest {
    @MockK
    val mockMoviesApiService = mockk<ApiServiceInterface>()

    @Test
    fun `when we fetch latest movies,then we get a list of movies`() = runTest{
        coEvery {
            mockMoviesApiService.getLatestMovies(
                any(),
                any(),
                any(),
                any(),
                any()
            )
        } returns Response.success(fakeSuccessMovieResponse)

        val remoteDataSource = createRemoteDataSource(
            moviesApiService = mockMoviesApiService,
        )

        val moviesRepository = createMoviesRepository(
            remoteDataSource = remoteDataSource
        )

        val movies = moviesRepository.fetchLatestMovies()
        val expectedResults = fakeSuccessMappedResponse

        Truth.assertThat(movies).isInstanceOf(Result.Success::class.java)
        Truth.assertThat((movies as Result.Success).data).isEqualTo(expectedResults)
    }

    @Test
    fun `when we fetch latest movies and a server error occurs,then we get the generic error type`() =
        runTest{
            coEvery {
                mockMoviesApiService.getLatestMovies(
                    any(),
                    any(),
                    any(),
                    any(),
                    any()
                )
            } returns Response.error(500, "".toResponseBody())

            val remoteDataSource = createRemoteDataSource(
                moviesApiService = mockMoviesApiService,
            )

            val moviesRepository = createMoviesRepository(
                remoteDataSource = remoteDataSource
            )
            val movies = moviesRepository.fetchLatestMovies()

            // Then
            Truth.assertThat(movies).isInstanceOf(Result.Error::class.java)
            Truth.assertThat((movies as Result.Error).errorType).isEqualTo(ErrorType.GENERIC)
        }

    private fun createMoviesRepository(
        remoteDataSource: NetworkServiceSource
    ): MovieRepository = MovieRepositoryImpl(
        networkServiceSource = remoteDataSource
    )

    private fun createRemoteDataSource(
        moviesApiService: ApiServiceInterface = mockMoviesApiService,
    ): NetworkServiceSource = NetworkServiceSourceImpl(
        apiServiceInterface = moviesApiService,
    )
}