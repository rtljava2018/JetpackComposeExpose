package com.demo.jetpackcomposedemo.domain

import com.demo.jetpackcomposedemo.domain.model.Movies

interface MovieRepository {
    suspend fun fetchLatestMovies(): Result<Movies>

}
