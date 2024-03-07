package com.demo.domain

import com.demo.domain.Result
import com.demo.domain.model.Movies

interface MovieRepository {
    suspend fun fetchLatestMovies(): Result<Movies>

}
