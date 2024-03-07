package com.demo.jetpackcomposedemo.presenter.moviesdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.domain.model.Movie
import com.demo.domain.Result
import com.demo.domain.usecase.ILatestMovieDetailsUseCase
import com.demo.jetpackcomposedemo.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val iLatestMovieDetailsUseCase: ILatestMovieDetailsUseCase
):ViewModel() {
    private val _state = MutableStateFlow(MovieDetailsScreenState(isLoading = true))
    val state: StateFlow<MovieDetailsScreenState> = _state.asStateFlow()

    fun processIntent(movieScreenIntent: MovieDetailsScreenIntent) {
        when (movieScreenIntent) {
            is MovieDetailsScreenIntent.LoadMovieDetail -> {
                viewModelScope.launch {
                    val result = iLatestMovieDetailsUseCase(movieScreenIntent.movieId)
                    processResult(result)
                }
            }
        }
    }

    private fun processResult(result: Result<Movie>) {
        when (result) {
            is Result.Success -> {
                val movie = result.data
                setState {
                    onMovieDetailsLoaded(
                        movieId = movie.id,
                        title = movie.title,
                        overview = movie.overview,
                        posterUrl = movie.posterUrl,
                        backdropUrl = movie.backdropUrl,
                        releaseDate = movie.releaseDate
                    )
                }
            }

            is Result.Error -> {
                setState {
                    onError(errorMsg =  R.string.error_response)
                }
            }
        }
    }

    private fun setState(stateReducer: MovieDetailsScreenState.() -> MovieDetailsScreenState) {
        viewModelScope.launch {
            _state.emit(stateReducer(state.value))
        }
    }
}