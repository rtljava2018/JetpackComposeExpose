package com.demo.jetpackcomposedemo.domain.usecase

import com.demo.jetpackcomposedemo.domain.model.Movie
import com.demo.jetpackcomposedemo.domain.Result


interface ILatestMovieDetailsUseCase {

    suspend operator fun invoke(movieId: Int): Result<Movie>
}
