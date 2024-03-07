package com.demo.data

import com.demo.domain.ErrorType
import com.demo.domain.MovieRepository
import com.demo.domain.Result
import com.demo.domain.model.Movies
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