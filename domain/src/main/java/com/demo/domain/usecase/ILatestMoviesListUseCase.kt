package com.demo.domain.usecase

import com.demo.domain.Result
import com.demo.domain.model.Movies

interface ILatestMoviesListUseCase {
    suspend operator fun invoke(): Result<Movies>
}
