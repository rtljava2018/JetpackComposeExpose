package com.demo.data

import com.demo.domain.model.Movies


interface NetworkServiceSource {
    suspend fun getListMovies(): Movies
}