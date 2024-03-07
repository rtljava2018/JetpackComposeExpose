package com.demo.data.di


import com.demo.data.MovieRepositoryImpl
import com.demo.data.NetworkServiceSource
import com.demo.data.NetworkServiceSourceImpl
import com.demo.domain.MovieRepository
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
