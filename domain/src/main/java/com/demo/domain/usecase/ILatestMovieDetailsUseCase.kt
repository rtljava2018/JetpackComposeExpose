package com.demo.domain.usecase

import com.demo.domain.Result
import com.demo.domain.model.Movie


interface ILatestMovieDetailsUseCase {

    suspend operator fun invoke(movieId: Int): Result<Movie>
}
