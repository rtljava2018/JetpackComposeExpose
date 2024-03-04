package com.demo.jetpackcomposedemo.di

import com.demo.jetpackcomposedemo.data.MovieRepositoryImpl
import com.demo.jetpackcomposedemo.data.NetworkServiceSource
import com.demo.jetpackcomposedemo.data.NetworkServiceSourceImpl
import com.demo.jetpackcomposedemo.domain.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface RepositoryModule {

    @Binds
    fun bindMovieRepository(movieRepository: MovieRepositoryImpl): MovieRepository

    @Binds
    fun bindRemoteDataSource(networkServiceSource: NetworkServiceSourceImpl): NetworkServiceSource

}
