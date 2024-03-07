package com.demo.jetpackcomposedemo.presenter

import app.cash.turbine.test
import com.demo.domain.MovieRepository
import com.demo.domain.usecase.GetLatestMovieDetailsUseCase
import com.demo.domain.usecase.ILatestMovieDetailsUseCase
import com.demo.jetpackcomposedemo.MainCoroutineRule
import com.demo.jetpackcomposedemo.fakeSuccessMappedResponse
import com.demo.jetpackcomposedemo.presenter.moviesdetails.MovieDetailsScreenIntent
import com.demo.jetpackcomposedemo.presenter.moviesdetails.MovieDetailsScreenState
import com.demo.jetpackcomposedemo.presenter.moviesdetails.MovieDetailsViewModel
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import com.demo.jetpackcomposedemo.R
import com.demo.domain.Result

@OptIn(ExperimentalCoroutinesApi::class)
class MovieDetailsViewModelTest {
    @get:Rule
    val coroutineRule = MainCoroutineRule()

    @Test
    fun `when we init the viewmodel with a movie id, then fetch and display the movie`() = runTest {
        // Given
        val repository = mockk<MovieRepository> {
            coEvery { fetchLatestMovies() } returns Result.Success(fakeSuccessMappedResponse)
        }
        val useCase = GetLatestMovieDetailsUseCase(movieRepository = repository)

        // When
        val viewModel = createViewModel(
            iLatestMovieDetailsUseCase = useCase
        )

        viewModel.processIntent(MovieDetailsScreenIntent.LoadMovieDetail(movieId = 1))

        // Then
        val expectedState = MovieDetailsScreenState(
            isLoading = false,
            errorMsg = null,
            movieId = 1,
            title = "The One",
            overview = "Fake Movie Overview",
            posterUrl = "https://image.tmdb.org/t/p/w185/fake_poster_path",
            backdropUrl = "https://image.tmdb.org/t/p/w780/fake_backdrop_path",
            releaseDate = "2021-01-01"
        )

        viewModel.state.test {
            awaitItem().also { state ->
                Truth.assertThat(state).isEqualTo(expectedState)
            }
        }
    }

    @Test
    fun `when we init the viewmodel with a wrong movie id, then display the error screen`() = runTest {
        // Given
        val repository = mockk<MovieRepository> {
            coEvery { fetchLatestMovies() } returns Result.Success(fakeSuccessMappedResponse)
        }
        val useCase = GetLatestMovieDetailsUseCase(movieRepository = repository)

        // When
        val viewModel = createViewModel(
            iLatestMovieDetailsUseCase = useCase
        )

        viewModel.processIntent(MovieDetailsScreenIntent.LoadMovieDetail(movieId = 5))

        // Then
        val expectedState = MovieDetailsScreenState(
            errorMsg = R.string.error_response
        )

        viewModel.state.test {
            awaitItem().also { state ->
                Truth.assertThat(state).isEqualTo(expectedState)
            }
        }
    }

    private fun createViewModel(
        iLatestMovieDetailsUseCase: ILatestMovieDetailsUseCase
    ) = MovieDetailsViewModel(
        iLatestMovieDetailsUseCase = iLatestMovieDetailsUseCase
    )
}
