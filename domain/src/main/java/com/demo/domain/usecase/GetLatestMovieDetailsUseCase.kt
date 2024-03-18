package com.demo.domain.usecase


import com.demo.domain.ErrorType
import com.demo.domain.MovieRepository
import com.demo.domain.Result
import com.demo.domain.model.Movie
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
