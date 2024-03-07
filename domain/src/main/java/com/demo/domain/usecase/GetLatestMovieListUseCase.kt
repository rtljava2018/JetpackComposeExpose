package com.demo.domain.usecase


import com.demo.domain.MovieRepository
import com.demo.domain.model.Movies
import com.demo.domain.Result
import javax.inject.Inject

class GetLatestMovieListUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) : ILatestMoviesListUseCase {

    override suspend fun invoke(): Result<Movies> =
        movieRepository.fetchLatestMovies()

}
