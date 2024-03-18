package com.demo.jetpackcomposedemo.presenter.movieslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.jetpackcomposedemo.R
import com.demo.domain.Result
import com.demo.domain.model.Movies
import com.demo.domain.usecase.ILatestMoviesListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainMoviesViewModel @Inject constructor(
    private val getLatestMoviesListUseCase: ILatestMoviesListUseCase
):ViewModel() {
    private val _state = MutableStateFlow(MoviesScreenState(isLoading = true))
    val state: StateFlow<MoviesScreenState> = _state.asStateFlow()
    init {
        initIntentProcess(MoviesScreenIntent.LoadLatestMovies)
    }

    fun initIntentProcess(loadLatestMovies: MoviesScreenIntent.LoadLatestMovies) {
        when (loadLatestMovies) {
            is MoviesScreenIntent.LoadLatestMovies -> {
                viewModelScope.launch {
                    val result = getLatestMoviesListUseCase()
                    processResult(result)
                }
            }
        }
    }

    private fun processResult(result: Result<Movies>) {
        when(result){
            is Result.Success->{
                val movies = result.data.movies
                setState {
                    onDataLoaded(movies = movies)
                }
            }
            is Result.Error ->{
                setState {
                    onError(
                        errorMsg = R.string.error_response
                    )
                }
            }
        }
    }

    private fun setState(dataLoad: MoviesScreenState.() -> MoviesScreenState) {
        viewModelScope.launch {
            _state.emit(dataLoad(state.value))
        }
    }
}