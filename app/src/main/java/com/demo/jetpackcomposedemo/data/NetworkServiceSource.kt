package com.demo.jetpackcomposedemo.data

import com.demo.jetpackcomposedemo.domain.model.Movies

interface NetworkServiceSource {
    suspend fun getListMovies(): Movies
}