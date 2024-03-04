package com.demo.jetpackcomposedemo.presenter.moviesdetails

sealed class MovieDetailsScreenIntent {
    data class LoadMovieDetail(val movieId: Int) : MovieDetailsScreenIntent()
}