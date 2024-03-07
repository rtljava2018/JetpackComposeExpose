package com.demo.jetpackcomposedemo.presenter


import app.cash.turbine.test
import com.demo.jetpackcomposedemo.MainCoroutineRule
import com.demo.domain.ErrorType
import com.demo.domain.MovieRepository
import com.demo.domain.Result
import com.demo.domain.usecase.GetLatestMovieListUseCase
import com.demo.domain.usecase.ILatestMoviesListUseCase
import com.demo.jetpackcomposedemo.fakeSuccessMappedResponse
import com.demo.jetpackcomposedemo.presenter.movieslist.MainMoviesViewModel
import com.demo.jetpackcomposedemo.presenter.movieslist.MoviesScreenState
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.Test
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import com.demo.jetpackcomposedemo.R

@OptIn(ExperimentalCoroutinesApi::class)
class MainMoviesViewModelTest {

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    @Test
    fun `when we init the viewmodel, then fetch and display the movies`() = runTest {
        val movieRepository = mockk<MovieRepository> {
            coEvery { fetchLatestMovies() } returns Result.Success(fakeSuccessMappedResponse)
        }

        val movieListUseCase = GetLatestMovieListUseCase(movieRepository = movieRepository)
        val viewModel = createViewModel(
            getLatestMoviesListUseCase = movieListUseCase,
        )

        val expectedState = MoviesScreenState(
            isLoading = false,
            movies = fakeSuccessMappedResponse.movies,
            errorMsg = null
        )

        viewModel.state.test {
            awaitItem().also { state ->
               assertThat(state).isEqualTo(expectedState)
            }
        }
    }

    @Test
    fun `when we init the viewmodel and an error occurs, then display the error screen`() =
        runTest {
            val movieRepository = mockk<MovieRepository> {
                coEvery { fetchLatestMovies() } returns Result.Error(ErrorType.GENERIC)
            }
            val movieListUseCase =
                GetLatestMovieListUseCase(movieRepository = movieRepository)


            val viewModel = createViewModel(
                getLatestMoviesListUseCase = movieListUseCase,
            )

            val expectedState = MoviesScreenState(
                isLoading = false,
                movies = emptyList(),
                errorMsg = R.string.error_response
            )

            viewModel.state.test {
                awaitItem().also { state ->
                    assertThat(state).isEqualTo(expectedState)
                }
            }
        }

    private fun createViewModel(
        getLatestMoviesListUseCase: ILatestMoviesListUseCase,
    ) =
        MainMoviesViewModel(
            getLatestMoviesListUseCase = getLatestMoviesListUseCase,
        )
}
