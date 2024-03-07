package com.demo.data

import com.demo.data.model.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServiceInterface {
    @GET("discover/movie")
    suspend fun getLatestMovies(
        @Query("language") language: String,
        @Query("page") page: Int,
        @Query("sort_by") sortBy: String,
        @Query("include_adult") includeAdult: Boolean,
        @Query("include_video") includeVideo: Boolean
    ): Response<MoviesResponse>
}