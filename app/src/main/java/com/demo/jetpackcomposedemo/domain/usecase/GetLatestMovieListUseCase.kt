package com.demo.jetpackcomposedemo.domain.usecase

import com.demo.jetpackcomposedemo.domain.MovieRepository
import com.demo.jetpackcomposedemo.domain.model.Movies
import com.demo.jetpackcomposedemo.domain.Result
import javax.inject.Inject

class GetLatestMovieListUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) : ILatestMoviesListUseCase {

    override suspend fun invoke(): Result<Movies> =
        movieRepository.fetchLatestMovies()

}
