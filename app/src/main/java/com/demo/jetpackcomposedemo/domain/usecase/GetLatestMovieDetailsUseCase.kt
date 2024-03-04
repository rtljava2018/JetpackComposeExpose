package com.demo.jetpackcomposedemo.domain.usecase

import com.demo.jetpackcomposedemo.domain.ErrorType
import com.demo.jetpackcomposedemo.domain.MovieRepository
import com.demo.jetpackcomposedemo.domain.model.Movie
import com.demo.jetpackcomposedemo.domain.Result
import javax.inject.Inject

class GetLatestMovieDetailsUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) : ILatestMovieDetailsUseCase {

    override suspend fun invoke(movieId: Int): Result<Movie> {
        return when (val result = movieRepository.fetchLatestMovies()) {
            is Result.Success -> {
                val movie = result.data.movies.find { it.id == movieId }
                if (movie == null) {
                    Result.Error(ErrorType.MOVIE_NOT_FOUND)
                } else {
                    Result.Success(movie)
                }
            }

            is Result.Error -> Result.Error(result.errorType)
        }
    }
}
