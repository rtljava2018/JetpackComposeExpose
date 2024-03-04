package com.demo.jetpackcomposedemo.data

import com.demo.jetpackcomposedemo.domain.ErrorType
import com.demo.jetpackcomposedemo.domain.MovieRepository
import com.demo.jetpackcomposedemo.domain.Result
import com.demo.jetpackcomposedemo.domain.model.Movies
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val networkServiceSource: NetworkServiceSource):MovieRepository {
    override suspend fun fetchLatestMovies(): Result<Movies> =try {
        val latestMovies = networkServiceSource.getListMovies()
        Result.Success(latestMovies)
    } catch (e: Throwable) {
        Result.Error(ErrorType.GENERIC)
    }
}