package com.demo.jetpackcomposedemo.presenter.movieslist

import androidx.annotation.StringRes
import com.demo.jetpackcomposedemo.domain.model.Movie

data class MoviesScreenState(
    val movies: List<Movie> = emptyList(),
    val isLoading: Boolean = false,
    @StringRes val errorMsg: Int? = null,
) {
    fun onDataLoaded(movies: List<Movie>): MoviesScreenState {
        return copy(movies = movies, isLoading = false, errorMsg = null)
    }

    fun onError(@StringRes errorMsg: Int): MoviesScreenState {
        return copy(isLoading = false, errorMsg = errorMsg)
    }
}
