package com.demo.jetpackcomposedemo.domain.usecase

import com.demo.jetpackcomposedemo.domain.Result
import com.demo.jetpackcomposedemo.domain.model.Movies


interface ILatestMoviesListUseCase {
    suspend operator fun invoke(): Result<Movies>
}
